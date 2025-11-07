//package com.example.kok.mapper;
//
//import com.example.kok.dto.AdminPaymentAdvertiseDTO;
//import com.example.kok.repository.AdminPaymentAdvertiseDAO;
//import com.example.kok.service.AdminPaymentAdvertiseService;
//import com.example.kok.util.Criteria;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class AdminPaymentAdvertiseTests {
//    @Autowired
//    private AdminPaymentAdvertiseMapper adminPaymentAdvertiseMapper;
//    @Autowired
//    private AdminPaymentAdvertiseDAO adminPaymentAdvertiseDAO;
//    @Autowired
//    private AdminPaymentAdvertiseService adminPaymentAdvertiseService;
//
////    Mapper
//    @Test
//    public void testSelectPaymentAdvertiseAllList(){
//        String keyword = "";
//        String category = "";
//        Criteria criteria = new Criteria(1, adminPaymentAdvertiseMapper.countAllPaymentAdvertise(keyword, category));
//        adminPaymentAdvertiseMapper.selectPaymentAdvertiseAllList(criteria, keyword, category).stream().map(AdminPaymentAdvertiseDTO::toString).forEach(log::info);
//        log.info("criteria: {}", criteria);
//        log.info("총 개수: {}", adminPaymentAdvertiseMapper.countAllPaymentAdvertise(keyword, category));
//    }
//
//    @Test
//    public void testCountAllPaymentAdvertise(){
//        String keyword = "";
//        String category = "";
//        log.info("총 개수: {}", adminPaymentAdvertiseMapper.countAllPaymentAdvertise(keyword, category));
//    }
//
//    @Test
//    public void testTotalAcceptPaymentAdvertise(){
//        log.info("결제 총액: {}", adminPaymentAdvertiseMapper.totalAcceptPaymentAdvertise());
//    }
//
//    @Test
//    public void testCountAcceptPaymentAdvertise(){
//        log.info("결제 개수: {}", adminPaymentAdvertiseMapper.countAcceptPaymentAdvertise());
//    }
//
////    DAO
//    @Test
//    public void testGetPaymentAdvertiseList(){
//        String keyword = "";
//        String category = "";
//        Criteria criteria = new Criteria(1, adminPaymentAdvertiseDAO.countListAll(keyword, category));
//        adminPaymentAdvertiseDAO.getPaymentAdvertiseList(criteria, keyword, category).stream().map(AdminPaymentAdvertiseDTO::toString).forEach(log::info);
//        log.info("criteria: {}", criteria);
//        log.info("총 개수: {}", adminPaymentAdvertiseDAO.countListAll(keyword, category));
//    }
//
//    @Test
//    public void testCountListAll(){
//        String keyword = "";
//        String category = "";
//        log.info("총 개수: {}", adminPaymentAdvertiseDAO.countListAll(keyword, category));
//    }
//
//    @Test
//    public void testCountAccept(){
//        log.info("승인된 결제 총액: {}", adminPaymentAdvertiseDAO.countAccept());
//    }
//
//    @Test
//    public void testTotalAccept(){
//        log.info("승인된 결제 총액: {}", adminPaymentAdvertiseDAO.totalAccept());
//    }
//
////    Service
//    @Test
//    public void testPaymentAdvertiseList(){
//        String keyword = "";
//        String category = "";
//        log.info("testPaymentAdvertiseList: {}", adminPaymentAdvertiseService.paymentAdvertiseList(1, keyword, category));
//    }
//
//}
