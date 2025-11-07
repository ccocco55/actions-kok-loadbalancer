package com.example.kok.service;

import com.example.kok.dto.AdminPaymentAdvertiseCountDTO;
import com.example.kok.dto.AdminPaymentAdvertiseCriteriaDTO;
import com.example.kok.dto.AdminPaymentAdvertiseDTO;
import com.example.kok.repository.AdminPaymentAdvertiseDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminPaymentAdvertiseServiceImpl implements AdminPaymentAdvertiseService {
    private final AdminPaymentAdvertiseDAO adminPaymentAdvertiseDAO;

//    광고 결제 목록
    @Override
    public AdminPaymentAdvertiseCriteriaDTO paymentAdvertiseList(int page, String keyword, String category) {
        AdminPaymentAdvertiseCriteriaDTO adminPaymentAdvertiseCriteriaDTO = new AdminPaymentAdvertiseCriteriaDTO();
        Criteria criteria = new Criteria(page, adminPaymentAdvertiseDAO.countListAll(keyword, category));
        List<AdminPaymentAdvertiseDTO> paymentAdvertiseList = adminPaymentAdvertiseDAO.getPaymentAdvertiseList(criteria, keyword, category);
        paymentAdvertiseList.forEach((payment) -> {
            String relativeDate = DateUtils.getCreatedDate(payment.getPaymentPaidDatetime());
            payment.setRelativeDate(relativeDate);
            String paymentPriceText = String.format("%,d", payment.getPaymentPrice());
            payment.setPaymentPriceText(paymentPriceText);
        });

        criteria.setHasMore(paymentAdvertiseList.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

//        11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            paymentAdvertiseList.remove(paymentAdvertiseList.size()-1);
        }

        AdminPaymentAdvertiseCountDTO countDTO = new AdminPaymentAdvertiseCountDTO();
        countDTO.setAcceptTotal(adminPaymentAdvertiseDAO.totalAccept().getAcceptTotal());
        countDTO.setAcceptCount(adminPaymentAdvertiseDAO.countAccept().getAcceptCount());
        String acceptTotalText = String.format("%,d", countDTO.getAcceptTotal());
        countDTO.setAcceptTotalText(acceptTotalText);

        adminPaymentAdvertiseCriteriaDTO.setPaymentAdvertiseList(paymentAdvertiseList);
        adminPaymentAdvertiseCriteriaDTO.setCriteria(criteria);
        adminPaymentAdvertiseCriteriaDTO.setAcceptCountTotal(countDTO);
        return adminPaymentAdvertiseCriteriaDTO;
    }
}
