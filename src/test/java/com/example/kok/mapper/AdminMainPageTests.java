//package com.example.kok.mapper;
//
//import com.example.kok.repository.AdminMainPageDAO;
//import com.example.kok.service.AdminMainPageService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class AdminMainPageTests {
//    @Autowired
//    private AdminMainPageMapper adminMainPageMapper;
//    @Autowired
//    private AdminMainPageDAO adminMainPageDAO;
//    @Autowired
//    private AdminMainPageService adminMainPageService;
//
////    Mapper
//    @Test
//    public void testMemberExperienceRequestAverage(){
//        log.info("회원 체험 신청 평균: {}", adminMainPageMapper.memberExperienceRequestAverage());
//    }
//
//    @Test
//    public void testMemberInternRequestAverage(){
//        log.info("회원 인턴 신청 평균: {}", adminMainPageMapper.memberInternRequestAverage());
//    }
//
//    @Test
//    public void testCompanyExperienceNoticeAverage(){
//        log.info("기업 체험 공고 평균: {}", adminMainPageMapper.companyExperienceNoticeAverage());
//    }
//
//    @Test
//    public void testCompanyInternNoticeAverage(){
//        log.info("기업 인턴 공고 평균: {}", adminMainPageMapper.companyInternNoticeAverage());
//    }
//
//    @Test
//    public void testMemberExperienceRequestTotal(){
//        log.info("회원 체험 총 개수: {}", adminMainPageMapper.memberExperienceRequestTotal());
//    }
//
//    @Test
//    public void testMemberInternRequestTotal(){
//        log.info("회원 인턴 총 개수: {}", adminMainPageMapper.memberInternRequestTotal());
//    }
//
//    @Test
//    public void testCompanyExperienceNoticeTotal(){
//        log.info("기업 체험 총 개수: {}", adminMainPageMapper.companyExperienceNoticeTotal());
//    }
//
//    @Test
//    public void testCompanyInternNoticeTotal(){
//        log.info("기업 인턴 총 개수: {}", adminMainPageMapper.companyInternNoticeTotal());
//    }
//
//    @Test
//    public void testMemberPaymentTotal(){
//        log.info("회원 결제 총 금액: {}", adminMainPageMapper.memberPaymentTotal());
//    }
//
//    @Test
//    public void testCompanyPaymentTotal(){
//        log.info("기업 결제 총 금액: {}", adminMainPageMapper.companyPaymentTotal());
//    }
//
//    @Test
//    public void testSelectCountRequestExperienceByMonth(){
//        String year ="2025";
//        String month = "08";
//        log.info("해당 달의 신청 개수: {}", adminMainPageMapper.selectCountRequestExperienceByMonth(year, month));
//    }
//
//    @Test
//    public void testSelectCountExperienceNoticeByMonth(){
//        String year ="2025";
//        String month = "08";
//        log.info("해당 달의 공고 개수: {}", adminMainPageMapper.selectCountExperienceNoticeByMonth(year, month));
//    }
//
//    @Test
//    public void testCountExperiencePaymentByMonth(){
//        String year ="2025";
//        String month = "10";
//        log.info("월간 체험 결재액: {}", adminMainPageMapper.countExperiencePaymentByMonth(year, month));
//    }
//
//    @Test
//    public void testCountAdvertisementPaymentByMonth(){
//        String year ="2025";
//        String month = "10";
//        log.info("월간 광고 결재액: {}", adminMainPageMapper.countAdvertisementPaymentByMonth(year, month));
//    }
//
////    DAO
//    @Test
//    public void testExperienceRequestAvg(){
//        log.info("회원 체험 신청 평균: {}", adminMainPageDAO.experienceRequestAvg());
//    }
//
//    @Test
//    public void testInternRequestAvg(){
//        log.info("회원 인턴 신청 평균: {}", adminMainPageDAO.internRequestAvg());
//    }
//
//    @Test
//    public void testExperienceNoticeAvg(){
//        log.info("기업 체험 공고 평균: {}", adminMainPageDAO.experienceNoticeAvg());
//    }
//
//    @Test
//    public void testInternNoticeAvg(){
//        log.info("기업 인턴 공고 평균: {}", adminMainPageDAO.internNoticeAvg());
//    }
//
//    @Test
//    public void testExperienceRequestTotal(){
//        log.info("회원 체험 총 개수: {}", adminMainPageDAO.experienceRequestTotal());
//    }
//
//    @Test
//    public void testInternRequestTotal(){
//        log.info("회원 인턴 총 개수: {}", adminMainPageDAO.internRequestTotal());
//    }
//
//    @Test
//    public void testExperienceNoticeTotal(){
//        log.info("기업 체험 총 개수: {}", adminMainPageDAO.experienceNoticeTotal());
//    }
//
//    @Test
//    public void testInternNoticeTotal(){
//        log.info("기업 인턴 총 개수: {}", adminMainPageDAO.internNoticeTotal());
//    }
//
//    @Test
//    public void testMemberPayTotal(){
//        log.info("회원 결제 총 금액: {}", adminMainPageDAO.memberPayTotal());
//    }
//
//    @Test
//    public void testCompanyPayTotal(){
//        log.info("기업 결제 총 금액: {}", adminMainPageDAO.companyPayTotal());
//    }
//
////    Service
//    @Test
//    public void testMainPage(){
//        log.info("AdminMainPageDAO 전체: {}", adminMainPageService.mainPage());
//    }
//
//    @Test
//    public void testMainPageChart(){
//        log.info("차트: {}", adminMainPageService.mainPageChart());
//    }
//
//}
