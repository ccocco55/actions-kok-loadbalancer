//package com.example.kok.mapper;
//
//import com.example.kok.dto.AdminExperienceDTO;
//import com.example.kok.dto.UserEvaluationDTO;
//import com.example.kok.dto.UserRequestExperienceDTO;
//import com.example.kok.repository.AdminExperienceDAO;
//import com.example.kok.service.AdminService;
//import com.example.kok.util.*;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//public class AdminExperienceTests {
//    @Autowired
//    private AdminExperienceMapper adminExperienceMapper;
//    @Autowired
//    private AdminExperienceDAO adminExperienceDAO;
//    @Autowired
//    private AdminService adminService;
//
//
////    Mapper
//    @Test
//    public void testAdminExperienceAll() {
//        String keyword = "그린";
//        AdminExperienceListCriteria criteria = new AdminExperienceListCriteria(1, adminExperienceMapper.selectAdminExperienceSearchCountAll(keyword));
//        adminExperienceMapper.selectAdminExperienceAll(criteria, keyword).stream().map(AdminExperienceDTO::toString).forEach(log::info);
//        log.info("{}", criteria);
//        log.info("{}", keyword);
//    }
//
//    @Test
//    public void testSelectAdminExperienceCountAll() {
//        String keyword = "";
//        log.info("전체 개수: {}", adminExperienceMapper.selectAdminExperienceSearchCountAll(keyword));
//    }
//
//    @Test
//    public void testSelectAdminExperienceById() {
//        Long id = 55L;
//        log.info("55번 게시글: {}", adminExperienceMapper.selectAdminExperienceById(id));
//    }
//
//    @Test
//    public void testSelectRequestUser() {
//        Long id = 54L;
//        AdminExperienceRequestCriteria adminExperienceCriteria = new AdminExperienceRequestCriteria(1, adminExperienceMapper.countRequestUser(id));
//        adminExperienceMapper.selectRequestUser(adminExperienceCriteria, id).stream().map(UserRequestExperienceDTO::toString).forEach(log::info);
//        log.info("adminExperienceCriteria: {}", adminExperienceCriteria);
//        log.info("신청자 수: {}", adminExperienceMapper.countRequestUser(id));
//    }
//
//    @Test
//    public void testCountRequestUser(){
//        Long id = 54L;
//        log.info("신청자 수: {}", adminExperienceMapper.countRequestUser(id));
//    }
//
//    @Test
//    public void testSelectUserEvaluation() {
//        Long id = 56L;
//        AdminExperienceCriteria adminExperienceCriteria = new AdminExperienceCriteria(2, adminExperienceMapper.countUserEvaluation(id));
//        adminExperienceMapper.selectUserEvaluation(adminExperienceCriteria, id).stream().map(UserEvaluationDTO::toString).forEach(log::info);
//        log.info("adminExperienceCriteria: {}", adminExperienceCriteria);
//        log.info("평가자 수: {}", adminExperienceMapper.countUserEvaluation(id));
//    }
//
//    @Test
//    public void testCountUserEvaluation() {
//        Long id = 56L;
//        log.info("평가자 수: {}", adminExperienceMapper.countUserEvaluation(id));
//    }
//
////    DAO
//    @Test
//    public void testAdminExperienceAllDAO() {
//        String keyword = "";
//        AdminExperienceListCriteria criteria = new AdminExperienceListCriteria(3, adminExperienceDAO.adminExperienceSearchCountAll(keyword));
//        adminExperienceDAO.adminExperienceAll(criteria, keyword).stream().map(AdminExperienceDTO::toString).forEach(log::info);
//        log.info("{}", criteria);
//    }
//
//    @Test
//    public void testAdminExperienceSearchCountAll() {
//        String keyword = "";
//        log.info("전체 개수{}", adminExperienceDAO.adminExperienceSearchCountAll(keyword));
//    }
//
//    @Test
//    public void testSelectAdminExperience() {
//        Long id = 55L;
//        log.info("55번 게시글: {}", adminExperienceDAO.selectAdminExperience(id));
//    }
//
//    @Test
//    public void testRequestUser() {
//        int page = 2;
//        Long id = 56L;
//        AdminExperienceRequestCriteria criteria = new AdminExperienceRequestCriteria(page, adminExperienceDAO.countRequestUser(id));
//        log.info("adminExperienceCriteria: {}", criteria);
//        log.info("requestUserCount: {}", adminExperienceDAO.countRequestUser(id));
//        adminExperienceDAO.requestUser(criteria, id).stream().map(UserRequestExperienceDTO::toString).forEach(log::info);
//    }
//
//
////    Service
//    @Test
//    public void testGetExperienceDetail() {
//        Long id = 55L;
//        log.info(adminService.getExperienceDetail(id).toString());
//    }
//}
