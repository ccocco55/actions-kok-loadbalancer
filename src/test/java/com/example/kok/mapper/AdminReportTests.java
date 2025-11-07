//package com.example.kok.mapper;
//
//import com.example.kok.dto.AdminReportDTO;
//import com.example.kok.repository.AdminReportDAO;
//import com.example.kok.service.AdminReportService;
//import com.example.kok.util.Criteria;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class AdminReportTests {
//    @Autowired
//    private AdminReportMapper adminReportMapper;
//    @Autowired
//    private AdminReportDAO adminReportDAO;
//    @Autowired
//    private AdminReportService adminReportService;
//
////    Mapper
//    @Test
//    public void testSelectReportAll() {
//        Criteria criteria = new Criteria(4, adminReportMapper.selectReportCount());
//        adminReportMapper.selectReportAll(criteria).stream().map(AdminReportDTO::toString).forEach(log::info);
//        log.info("{}", criteria);
//    }
//
//    @Test
//    public void testSelectReportCount() {
//        log.info("전체 개수: {}", adminReportMapper.selectReportCount());
//    }
//
//    @Test
//    public void testSelectAdminExperienceById() {
//        Long id = 31L;
//        log.info("31번 게시글: {}", adminReportMapper.selectReportDetail(id));
//    }
//
//    @Test
//    public void testDeleteReportPost(){
//        Long id = 36L;
//        log.info("삭제 전 전체 개수: {}", adminReportMapper.selectReportCount());
//        adminReportMapper.deleteReportPost(id);
//        log.info("삭제 후 전체 개수: {}", adminReportMapper.selectReportCount());
//    }
////test
//
////    DAO
//    @Test
//    public void testReportList(){
//        Criteria criteria = new Criteria(1, adminReportDAO.reportCount());
//        adminReportDAO.reportList(criteria).stream().map(AdminReportDTO::toString).forEach(log::info);
//        log.info("{}", criteria);
//    }
//
//    @Test
//    public void testReportCount(){
//        log.info("전체 개수: {}", adminReportDAO.reportCount());
//    }
//
//    @Test
//    public void testReportDetail(){
//        Long id = 31L;
//        log.info("31번 게시글: {}", adminReportDAO.reportDetail(id));
//    }
//
//    @Test
//    public void testDeleteReport(){
//        Long id = 36L;
//        log.info("삭제 전 전체 개수: {}", adminReportDAO.reportCount());
//        adminReportDAO.deleteReport(id);
//        log.info("삭제 후 전체 개수: {}", adminReportDAO.reportCount());
//    }
//
//
////    Service
//    @Test
//    public void testGetReportList(){
//        log.info("Service 목록: {}", adminReportService.getReportList(1));
//    }
//
//    @Test
//    public void testGetReportDetail(){
//        Long id = 38L;
//        log.info("38번 게시글: {}", adminReportService.getReportDetail(id).getPostFiles());
//    }
//
//    @Test
//    public void testDeleteReportPostService(){
//        Long id = 36L;
//        log.info("삭제 전 전체 개수: {}", adminReportDAO.reportCount());
//        adminReportService.deleteReportPost(36L);
//        log.info("삭제 후 전체 개수: {}", adminReportDAO.reportCount());
//    }
//}
