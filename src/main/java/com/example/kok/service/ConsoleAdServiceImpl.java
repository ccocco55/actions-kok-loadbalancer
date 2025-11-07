package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import com.example.kok.repository.*;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsoleAdServiceImpl implements ConsoleAdService {
    private final ConsoleAdDAO consoleAdDAO;
    private final PaymentDAO paymentDAO;
    private final PaymentUserDAO paymentUserDAO;
    private final S3Service s3Service;
    private final ConsoleAdFileDAO consoleAdFileDAO;
    private final ConsolePaymentDAO consolePaymentDAO;

    // 목록
    @Override
    public ConsoleAdNoticeCriteriaDTO getList(Long companyId, int page, String keyword) {
        ConsoleAdNoticeCriteriaDTO consoleAdNoticeCriteriaDTO = new ConsoleAdNoticeCriteriaDTO();

        int totalCount = consoleAdDAO.findCountByCompany(companyId, null, keyword);
        int activeCount = consoleAdDAO.findActiveCountByCompany(companyId, RequestStatus.ACCEPT, keyword);
        Long activeTotalPrice = consoleAdDAO.findActiveTotalPriceByCompany(companyId);
        Criteria criteria = new Criteria(page, totalCount);

        List<ConsoleAdNoticeDTO> notices = consoleAdDAO.findAllByCompany(companyId, criteria, keyword);

        criteria.setHasMore(notices.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            notices.remove(notices.size() - 1);
        }

        consoleAdNoticeCriteriaDTO.setAdLists(notices);
        consoleAdNoticeCriteriaDTO.setCriteria(criteria);
        consoleAdNoticeCriteriaDTO.setTotalCount(totalCount);
        consoleAdNoticeCriteriaDTO.setActiveTotalCount(activeCount);
        consoleAdNoticeCriteriaDTO.setActiveTotalPrice(activeTotalPrice);

        return consoleAdNoticeCriteriaDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "posts", key="'post_' + #id")
    public ConsoleAdNoticeDTO getDetail(Long id) {
        return consoleAdDAO.findDetailById(id);
    }

    @Override
    public void setPreSignedUrl(ConsoleAdNoticeDTO consoleAdDTO) {
        List<FileDTO> files = consoleAdFileDAO.findAllByAdvertisementId(consoleAdDTO.getId());

        files.forEach(file -> {
            if (file.getFilePath().contains("/images/experience/ad_bg_img.jpg")) {
                file.setFilePath("/images/experience/ad_bg_img.jpg");
            } else {
                file.setFilePath(
                        s3Service.getPreSignedUrl(file.getFilePath(), Duration.ofMinutes(5))
                );
            }
        });

        consoleAdDTO.setUploadedFiles(files);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerAdvertisement(ConsoleAdNoticeDTO consoleAdDTO, List<MultipartFile> multipartFiles) {
//        광고 등록
        consoleAdDAO.createAdvertisement(consoleAdDTO);

        // 파일 업로드
        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            multipartFiles.forEach(multipartFile -> {
                if (multipartFile.isEmpty()) return;

                try {
                    // S3 업로드
                    String s3Key = s3Service.uploadFile(multipartFile, getPath());

                    // 파일 DTO 구성
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
                    fileDTO.setFileName(UUID.randomUUID().toString());
                    fileDTO.setFilePath(s3Key);
                    fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
                    fileDTO.setFileContentType(multipartFile.getContentType());

                    // tbl_file 저장
                    consoleAdFileDAO.saveFile(fileDTO);

                    ConsoleAdNoticeFileDTO consoleFileDTO = new ConsoleAdNoticeFileDTO();
                    consoleFileDTO.setFileId(fileDTO.getId());
                    consoleFileDTO.setAdvertisementId(consoleAdDTO.getId());

                    // 광고-파일 연결
                    consoleAdFileDAO.linkFileToAdvertisement(consoleFileDTO);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        Long advertisementId = consoleAdDTO.getId();

//        결제 등록
        PaymentDTO payment = new PaymentDTO();
        payment.setAdvertisementId(advertisementId);
        payment.setUserId(consoleAdDTO.getCompanyId());
        payment.setPaymentPrice(consoleAdDTO.getPaymentPrice());
        payment.setPaymentStatus(Status.ACTIVE);
        paymentDAO.insertPayment(payment);

//        결제사용자 등록
        PaymentUserDTO paymentUser = new PaymentUserDTO();
        paymentUser.setPaymentId(payment.getId());
        paymentUser.setUserId(consoleAdDTO.getCompanyId());
        paymentUser.setAdvertisementId(advertisementId);
        paymentUserDAO.insertPaymentUser(paymentUser);
    }

    public String getPath() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return today.format(formatter);
    }

//    광고 수정
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "posts", key = "'post_' + #consoleAdDTO.id")
    public void updateAdvertisement(ConsoleAdNoticeDTO consoleAdDTO, List<MultipartFile> multipartFiles) {
        consoleAdDAO.editNotice(toConsoleAdVO(consoleAdDTO));

        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            multipartFiles.forEach((multipartFile) -> {
                if (multipartFile.isEmpty()) return;

                try {
                    String s3Key = s3Service.uploadFile(multipartFile, getPath());
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
                    fileDTO.setFileName(s3Key.substring(s3Key.lastIndexOf("/") + 1));
                    fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
                    fileDTO.setFilePath(s3Key);
                    fileDTO.setFileContentType(multipartFile.getContentType());

                    consoleAdFileDAO.saveFile(fileDTO);

                    ConsoleAdNoticeFileDTO consoleAdNoticeFileDTO = new ConsoleAdNoticeFileDTO();
                    consoleAdNoticeFileDTO.setFileId(fileDTO.getId());
                    consoleAdNoticeFileDTO.setAdvertisementId(consoleAdDTO.getId());

                    consoleAdFileDAO.linkFileToAdvertisement(consoleAdNoticeFileDTO);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }

//    광고 상태 변경
    @Override
    public void updateListStatus(Long noticeId, Status status) {
        consoleAdDAO.updateNoticeStatus(noticeId, status);
    }

    //    광고 삭제
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAdvertisement(Long advertisementId) {
//        광고-파일 연결 삭제
        consoleAdFileDAO.deleteAdFileLinks(advertisementId);

//        파일 삭제
        consoleAdFileDAO.deleteFiles(advertisementId);

//        결제 삭제
        consolePaymentDAO.deletePaymentUserByAdvertisementId(advertisementId);
        consolePaymentDAO.deletePaymentByAdvertisementId(advertisementId);

//        광고 삭제
        consoleAdDAO.deleteAdvertisementById(advertisementId);
    }

//    광고 마감 처리
    public void closeAd(){
        consoleAdDAO.setAdStatusToInactive();
    }

}
