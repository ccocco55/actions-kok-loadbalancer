package com.example.kok.service;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.dto.*;
import com.example.kok.repository.AdminAdvertisementDAO;
import com.example.kok.repository.AdvertisementBackgroundFileDAO;
import com.example.kok.repository.CommunityPostFileDAO;
import com.example.kok.util.AdminAdvertisementCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminAdvertisementServiceImpl implements AdminAdvertisementService {
    private final AdminAdvertisementDAO adminAdvertisementDAO;
    private final AdvertisementBackgroundFileDAO advertisementBackgroundFileDAO;
    private final S3Service s3Service;

//    광고 목록
    @Override
    public AdminAdvertisementCriteriaDTO advertisementList(int page, String keyword, String category) {
        AdminAdvertisementCriteriaDTO advertisementCriteriaDTO = new AdminAdvertisementCriteriaDTO();
        AdminAdvertisementCountDTO countDTO = adminAdvertisementDAO.countStatus();
        AdminAdvertisementCriteria criteria = new AdminAdvertisementCriteria(page, adminAdvertisementDAO.countAll(keyword, category));
        List<AdminAdvertisementDTO> advertisements = adminAdvertisementDAO.getAdvertisementList(criteria, keyword, category);

        criteria.setHasMore(advertisements.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

//        11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            advertisements.remove(advertisements.size()-1);
        }

        advertisementCriteriaDTO.setCountDTO(countDTO);
        advertisementCriteriaDTO.setAdvertisements(advertisements);
        advertisementCriteriaDTO.setCriteria(criteria);
        return advertisementCriteriaDTO;
    }

//    광고 상세
    @Override
    @Cacheable(value = "adminAdvertisement", key = "'adminAdvertisement_' + #id")
    public AdminAdvertisementDTO advertisementDetail(Long id) {
        AdminAdvertisementDTO advertisementDTO = adminAdvertisementDAO.selectAdvertisement(id).orElseThrow(PostNotFoundException::new);

        List<AdvertisementBackgroundFileDTO> files = advertisementBackgroundFileDAO.advertisementBackgroundFile(advertisementDTO.getId());
        files.forEach(file -> {
            file.setFilePath(s3Service.getPreSignedUrl(file.getFilePath(), Duration.ofMinutes(5)));
        });
        advertisementDTO.setAdvertisementBackgroundFiles(files);
        return advertisementDTO;
    }

//    광고 승인
    public void accept(Long id){
        adminAdvertisementDAO.acceptAdvertisement(id);
    }
//    광고 거절
    public void reject(Long id){
        adminAdvertisementDAO.rejectAdvertisement(id);
    }
}
