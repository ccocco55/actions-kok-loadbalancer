package com.example.kok.service;

import com.example.kok.dto.AdminMainPageDTO;
import com.example.kok.dto.ChartDTO;
import com.example.kok.repository.AdminMainPageDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminMainPageServiceImpl implements AdminMainPageService {
    private final AdminMainPageDAO adminMainPageDAO;

    @Override
    @Cacheable(value = "adminTable", key="'adminTable'")
    public AdminMainPageDTO mainPage() {
        AdminMainPageDTO mainPageDTO = new AdminMainPageDTO();
        mainPageDTO.setMemberExperienceRequestAvg(adminMainPageDAO.experienceRequestAvg());
        mainPageDTO.setMemberInternRequestAvg(adminMainPageDAO.internRequestAvg());
        mainPageDTO.setCompanyExperienceNoticeAvg(adminMainPageDAO.experienceNoticeAvg());
        mainPageDTO.setCompanyInternNoticeAvg(adminMainPageDAO.internNoticeAvg());
        mainPageDTO.setMemberExperienceRequestTotal(adminMainPageDAO.experienceRequestTotal());
        mainPageDTO.setMemberInternRequestTotal(adminMainPageDAO.internRequestTotal());
        int memberRequestTotal = mainPageDTO.getMemberExperienceRequestTotal() + mainPageDTO.getMemberInternRequestTotal();
        mainPageDTO.setMemberRequestTotal(memberRequestTotal);
        mainPageDTO.setCompanyExperienceNoticeTotal(adminMainPageDAO.experienceNoticeTotal());
        mainPageDTO.setCompanyInternNoticeTotal(adminMainPageDAO.internNoticeTotal());
        int companyNoticeTotal = mainPageDTO.getCompanyExperienceNoticeTotal() + mainPageDTO.getCompanyInternNoticeTotal();
        mainPageDTO.setCompanyNoticeTotal(companyNoticeTotal);
        mainPageDTO.setMemberPaymentTotal(adminMainPageDAO.memberPayTotal());
        String memberPaymentTotalText = String.format("%,d", mainPageDTO.getMemberPaymentTotal());
        mainPageDTO.setMemberPaymentTotalText(memberPaymentTotalText);
        mainPageDTO.setCompanyPaymentTotal(adminMainPageDAO.companyPayTotal());
        String companyPaymentTotalText = String.format("%,d", mainPageDTO.getCompanyPaymentTotal());
        mainPageDTO.setCompanyPaymentTotalText(companyPaymentTotalText);

        return mainPageDTO;
    }

    @Override
    @Cacheable(value = "adminChart", key = "'adminChart'")
    public List<ChartDTO> mainPageChart() {
        List<ChartDTO> chartDTOList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        for(int i = 0; i < 4; i++) {
            ChartDTO chartDTO = new ChartDTO();
            chartDTO.setMonth(localDateTime.getMonthValue() - i + "");
            chartDTO.setYear(localDateTime.getYear()+ "");
//            월간 체험 신청 수
            chartDTO.setMonthCount(adminMainPageDAO.selectCountRequestExperience(chartDTO.getYear(), String.format("%02d", localDateTime.getMonthValue() - i)));
//            월간 체험 공고 수
            chartDTO.setExperienceMonthCount(adminMainPageDAO.selectCountExperienceNotice(chartDTO.getYear(), String.format("%02d",localDateTime.getMonthValue() - i )));
//            월간 인턴 신청 수
            chartDTO.setInternRequestMonthCount(adminMainPageDAO.selectCountInternRequest(chartDTO.getYear(), String.format("%02d",localDateTime.getMonthValue() - i)));
//            월간 인턴 공고 수
            chartDTO.setInternNoticeMonthCount(adminMainPageDAO.selectCountInternNotice(chartDTO.getYear(), String.format("%02d",localDateTime.getMonthValue() - i)));
//            월간 체험 결재액
            chartDTO.setExperiencePayment(adminMainPageDAO.countExperiencePayment(chartDTO.getYear(), String.format("%02d", localDateTime.getMonthValue() - i)));
//            월간 광고 결재액
            chartDTO.setAdvertisementPayment(adminMainPageDAO.countAdvertisementPayment(chartDTO.getYear(), String.format("%02d", localDateTime.getMonthValue() - i)));
            chartDTOList.add(chartDTO);
        }

        return chartDTOList;
    }
}
