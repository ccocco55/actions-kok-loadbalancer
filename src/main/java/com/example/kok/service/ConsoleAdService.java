package com.example.kok.service;

import com.example.kok.domain.ConsoleAdNoticeVO;
import com.example.kok.dto.ConsoleAdNoticeCriteriaDTO;
import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.enumeration.Status;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ConsoleAdService {
//    광고 목록
    public ConsoleAdNoticeCriteriaDTO getList(Long companyId, int page, String keyword);

//    광고 상세
    public ConsoleAdNoticeDTO getDetail(Long advertisementId);
    public void setPreSignedUrl(ConsoleAdNoticeDTO consoleAdNoticeDTO);

//    광고 등록
    public void registerAdvertisement(ConsoleAdNoticeDTO adNoticeDTO, List<MultipartFile> multipartFiles);

//    광고 수정
    public void updateAdvertisement(ConsoleAdNoticeDTO adNoticeDTO, List<MultipartFile> multipartFiles);

//    광고 상태 변경
    public void updateListStatus(Long noticeId, Status status);

//    광고 삭제
    public void deleteAdvertisement(Long advertisementId);

//    광고 마감 처리
    public void closeAd();

    default ConsoleAdNoticeVO toConsoleAdVO(ConsoleAdNoticeDTO consoleAdDTO){
        return ConsoleAdNoticeVO.builder()
                .id(consoleAdDTO.getId())
                .advertisementMainText(consoleAdDTO.getAdvertisementMainText())
                .advertisementSubText(consoleAdDTO.getAdvertisementSubText())
                .advertisementStatus(consoleAdDTO.getAdvertisementStatus())
                .advertisementRequestStatus(consoleAdDTO.getAdvertisementRequestStatus())
                .advertiseStartDatetime(consoleAdDTO.getAdvertiseStartDatetime())
                .advertiseEndDatetime(consoleAdDTO.getAdvertiseEndDatetime())
                .companyId(consoleAdDTO.getCompanyId())
                .paymentPrice(consoleAdDTO.getPaymentPrice())
                .build();
    }

}
