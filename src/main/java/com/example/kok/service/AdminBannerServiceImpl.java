package com.example.kok.service;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.dto.BannerFileCriteriaDTO;
import com.example.kok.dto.BannerFileDTO;
import com.example.kok.repository.AdminAdvertisementDAO;
import com.example.kok.repository.AdminBannerDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminBannerServiceImpl implements AdminBannerService {
    private final AdminBannerDAO adminBannerDAO;
    private final S3Service s3Service;
    private final BannerFileDTO bannerFileDTO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(List<MultipartFile> multipartFiles) {

        multipartFiles.forEach((multipartFile) -> {
            if(multipartFile.getOriginalFilename().equals("")){
                return;
            }

            try {
                String s3Key = s3Service.uploadFile(multipartFile, getPath());
                BannerFileDTO bannerFileDTO = new BannerFileDTO();

                bannerFileDTO.setBannerFileOriginName(multipartFile.getOriginalFilename());
                bannerFileDTO.setBannerFileName(s3Key.substring(s3Key.lastIndexOf("/") + 1));
                bannerFileDTO.setBannerFilePath(s3Key);
                bannerFileDTO.setBannerFileSize(String.valueOf(multipartFile.getSize()));
                bannerFileDTO.setBannerFileContentType(multipartFile.getContentType());
                bannerFileDTO.setBannerFileContentType(bannerFileDTO.getBannerFileContentType().split("/")[1]);

                adminBannerDAO.insertFile(bannerFileDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public BannerFileCriteriaDTO bannerList(int page) {
        BannerFileCriteriaDTO bannerFileCriteriaDTO = new BannerFileCriteriaDTO();
        Criteria criteria = new Criteria(page, adminBannerDAO.countAll());
        List<BannerFileDTO> bannerFiles = adminBannerDAO.selectAllFile(criteria);
        bannerFiles.forEach((file) -> {
            String relativeDate = DateUtils.getCreatedDate(file.getCreatedDateTime());
            file.setRelativeDate(relativeDate);
            file.setBannerFilePath(s3Service.getPreSignedUrl(file.getBannerFilePath(), Duration.ofMinutes(5)));
        });

        criteria.setHasMore(bannerFiles.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

//        11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            bannerFiles.remove(bannerFiles.size()-1);
        }

        bannerFileCriteriaDTO.setBannerFileList(bannerFiles);
        bannerFileCriteriaDTO.setCriteria(criteria);
        return bannerFileCriteriaDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        List<BannerFileDTO> bannerFiles = adminBannerDAO.selectAllFileById(id);
        bannerFiles.forEach((bannerFile) -> {
            s3Service.deleteFile(bannerFile.getBannerFilePath());
        });
        adminBannerDAO.deleteFile(id);
    }

    public String getPath() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return today.format(formatter);
    }
}
