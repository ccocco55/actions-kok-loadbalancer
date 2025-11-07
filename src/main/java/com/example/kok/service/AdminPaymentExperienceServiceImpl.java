package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.repository.AdminPaymentExperienceDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminPaymentExperienceServiceImpl implements AdminPaymentExperienceService {
    private final AdminPaymentExperienceDAO adminPaymentExperienceDAO;

//    체험 목록
    @Override
    public AdminPaymentExperienceCriteriaDTO getPaymentExperienceList(int page, String keyword, String category) {
        AdminPaymentExperienceCriteriaDTO adminPaymentExperienceCriteriaDTO = new AdminPaymentExperienceCriteriaDTO();
        Criteria criteria = new Criteria(page, adminPaymentExperienceDAO.countListAll(keyword, category));
        List<AdminPaymentExperienceDTO> paymentExperienceList = adminPaymentExperienceDAO.paymentExperienceList(criteria, keyword, category);
        paymentExperienceList.forEach((payment) -> {
            String relativeDate = DateUtils.getCreatedDate(payment.getPaymentPaidDatetime());
            payment.setRelativeDate(relativeDate);
            String paymentPriceText = String.format("%,d", payment.getPaymentPrice());
            payment.setPaymentPriceText(paymentPriceText);
        });

        criteria.setHasMore(paymentExperienceList.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

//        11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            paymentExperienceList.remove(paymentExperienceList.size()-1);
        }

        AdminPaymentExperienceCountDTO countDTO = new AdminPaymentExperienceCountDTO();
        countDTO.setAcceptTotal(adminPaymentExperienceDAO.totalAccept().getAcceptTotal());
        String acceptTotalText = String.format("%,d", countDTO.getAcceptTotal());
        countDTO.setRejectTotal(adminPaymentExperienceDAO.totalReject().getRejectTotal());
        String rejectTotalText = String.format("%,d", countDTO.getRejectTotal());
        countDTO.setAcceptTotalText(acceptTotalText);
        countDTO.setRejectTotalText(rejectTotalText);

        adminPaymentExperienceCriteriaDTO.setPaymentExperienceList(paymentExperienceList);
        adminPaymentExperienceCriteriaDTO.setCriteria(criteria);
        adminPaymentExperienceCriteriaDTO.setPaymentExperienceCount(countDTO);
        return adminPaymentExperienceCriteriaDTO;
    }

}
