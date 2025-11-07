//package com.example.kok.mapper;
//
//import com.example.kok.dto.AdminPaymentExperienceDTO;
//import com.example.kok.repository.AdminPaymentExperienceDAO;
//import com.example.kok.util.Criteria;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class AdminPaymentExperienceTests {
//    @Autowired
//    private AdminPaymentExperienceMapper adminPaymentExperienceMapper;
//    @Autowired
//    private AdminPaymentExperienceDAO adminPaymentExperienceDAO;
//
////    Mapper
//    @Test
//    public void testSelectPaymentExperienceAllList(){
//        String keyword = "";
//        String category = "";
//        Criteria criteria = new Criteria(1, adminPaymentExperienceMapper.countAllPaymentExperience(keyword, category));
//        adminPaymentExperienceMapper.selectPaymentExperienceAllList(criteria, keyword, category).stream().map(AdminPaymentExperienceDTO::toString).forEach(log::info);
//        log.info("criteria: {}", criteria);
//    }
//
//    @Test
//    public void testCountAllPaymentExperience(){
//        String keyword = "";
//        String category = "";
//        log.info("전체 개수: {}", adminPaymentExperienceMapper.countAllPaymentExperience(keyword, category));
//    }
//
//    @Test
//    public void testTotalAcceptPaymentExperience(){
//        log.info("승인 총액: {}", adminPaymentExperienceMapper.totalAcceptPaymentExperience());
//    }
//
//    @Test
//    public void testTotalRejectPaymentExperience(){
//        log.info("거절 총액: {}", adminPaymentExperienceMapper.totalRejectPaymentExperience());
//    }
//
////    DAO
//    @Test
//    public void testPaymentExperienceList(){
//        String keyword = "";
//        String category = "";
//        Criteria criteria = new Criteria(1, adminPaymentExperienceDAO.countListAll(keyword, category));
//        adminPaymentExperienceDAO.paymentExperienceList(criteria, keyword, category).stream().map(AdminPaymentExperienceDTO::toString).forEach(log::info);
//        log.info("criteria: {}", criteria);
//    }
//
//    @Test
//    public void testCountListAll(){
//        String keyword = "";
//        String category = "";
//        log.info("전체 개수: {}", adminPaymentExperienceDAO.countListAll(keyword, category));
//    }
//
//    @Test
//    public void testTotalAccept(){
//        log.info("승인 총액: {}", adminPaymentExperienceDAO.totalAccept());
//    }
//
//    @Test
//    public void testTotalReject(){
//        log.info("승인 총액: {}", adminPaymentExperienceDAO.totalReject());
//    }
//
////    Service
//}
