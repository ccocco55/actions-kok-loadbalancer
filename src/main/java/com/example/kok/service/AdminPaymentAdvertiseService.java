package com.example.kok.service;

import com.example.kok.dto.AdminPaymentAdvertiseCriteriaDTO;

public interface AdminPaymentAdvertiseService {
//    광고 결제 목록
    public AdminPaymentAdvertiseCriteriaDTO paymentAdvertiseList(int page, String keyword, String category);
}
