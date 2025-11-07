package com.example.kok.repository;

import com.example.kok.dto.AdminMainPageDTO;
import com.example.kok.mapper.AdminMainPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminMainPageDAO {
    private final AdminMainPageMapper adminMainPageMapper;

//    회원 - 평균 체험 지원 수
    public double experienceRequestAvg(){
        return adminMainPageMapper.memberExperienceRequestAverage();
    }

//    회원 - 평균 인턴 지원 수
    public double internRequestAvg(){
        return adminMainPageMapper.memberInternRequestAverage();
    }

//    기업 - 평균 체험 모집 수
    public double experienceNoticeAvg(){
        return adminMainPageMapper.companyExperienceNoticeAverage();
    }

//    기업 - 평균 인턴 모집 수
    public double internNoticeAvg(){
        return adminMainPageMapper.companyInternNoticeAverage();
    }

//    회원 - 체험 총 개수
    public int experienceRequestTotal(){
        return adminMainPageMapper.memberExperienceRequestTotal();
    }

//    회원 - 인턴 총 개수
    public int internRequestTotal(){
        return adminMainPageMapper.memberInternRequestTotal();
    }

//    기업 - 체험 총 개수
    public int experienceNoticeTotal(){
        return adminMainPageMapper.companyExperienceNoticeTotal();
    }

//    기업 - 인턴 총 개수
    public int internNoticeTotal(){
        return adminMainPageMapper.companyInternNoticeTotal();
    }

//    회원 - 결제 총 금액
    public Long memberPayTotal(){
        return adminMainPageMapper.memberPaymentTotal();
    }

//    기업 - 결제 총 금액
    public Long companyPayTotal(){
        return adminMainPageMapper.companyPaymentTotal();
    }

//    차트
//    월간 체험 신청 수
    public int selectCountRequestExperience(String year, String month){
        return adminMainPageMapper.selectCountRequestExperienceByMonth(year, month);
    }
//    월간 체험 공고 수
    public int selectCountExperienceNotice(String year, String month){
        return adminMainPageMapper.selectCountExperienceNoticeByMonth(year, month);
    }
//    월간 인턴 신청 수
    public int selectCountInternRequest(String year, String month){
        return adminMainPageMapper.selectCountInternRequestByMonth(year, month);
    }
//    월간 인턴 공고 수
    public int selectCountInternNotice(String year, String month){
        return adminMainPageMapper.selectCountInternNoticeByMonth(year, month);
    }
//    월간 체험 결재액
    public Long countExperiencePayment(String year, String month){
        return adminMainPageMapper.countExperiencePaymentByMonth(year, month);
    }
//    월간 광고 결재액
    public Long countAdvertisementPayment(String year, String month){
        return adminMainPageMapper.countAdvertisementPaymentByMonth(year, month);
    }
}
