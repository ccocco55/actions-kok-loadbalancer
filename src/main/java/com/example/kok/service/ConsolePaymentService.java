package com.example.kok.service;

import com.example.kok.dto.ConsolePaymentCriteriaDTO;

public interface ConsolePaymentService {
//    결제 목록
    public ConsolePaymentCriteriaDTO getList(Long companyId, int page);

}
