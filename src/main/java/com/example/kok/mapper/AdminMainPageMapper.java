package com.example.kok.mapper;

import com.example.kok.dto.AdminMainPageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMainPageMapper {
//    회원 - 평균 체험 지원 수
    public double memberExperienceRequestAverage();

//    회원 - 평균 인턴 지원 수
    public double memberInternRequestAverage();

//    기업 - 평균 체험 모집 수
    public double companyExperienceNoticeAverage();

//    기업 - 평균 인턴 모집 수
    public double companyInternNoticeAverage();

//    회원 - 체험 총 개수
    public int memberExperienceRequestTotal();

//    회원 - 인턴 총 개수
    public int memberInternRequestTotal();

//    기업 - 체험 총 개수
    public int companyExperienceNoticeTotal();
    
//    기업 - 인턴 총 개수
    public int companyInternNoticeTotal();

//    회원 - 결제 총 금액
    public Long memberPaymentTotal();

//    기업 - 결제 총 금액
    public Long companyPaymentTotal();

//    차트
//    월간 체험 신청 수
    public int selectCountRequestExperienceByMonth(String year, String month);
//    월간 체험 공고 수
    public int selectCountExperienceNoticeByMonth(String year, String month);
//    월간 인턴 신청 수
    public int selectCountInternRequestByMonth(String year, String month);
//    월간 인턴 공고 수
    public int selectCountInternNoticeByMonth(String year, String month);
//    월간 체험 결재액
    public Long countExperiencePaymentByMonth(String year, String month);
//    월간 광고 결재액
    public Long countAdvertisementPaymentByMonth(String year, String month);
}
