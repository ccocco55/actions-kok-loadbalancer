package com.example.kok.service;

import ch.qos.logback.classic.pattern.LocalSequenceNumberConverter;
import com.example.kok.dto.AdminAdvertisementCriteriaDTO;
import com.example.kok.dto.AdminAdvertisementDTO;
import com.example.kok.util.Search;

public interface AdminAdvertisementService {
//    광고 목록
    public AdminAdvertisementCriteriaDTO advertisementList(int page, String keyword, String category);

//    광고 상세
    public AdminAdvertisementDTO advertisementDetail(Long id);

//    광고 승인
    public void accept(Long id);

//    광고 거절
    public void reject(Long id);
}
