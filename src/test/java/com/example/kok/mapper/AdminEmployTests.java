//package com.example.kok.mapper;
//
//import com.example.kok.dto.AdminInternNoticeDetailRequestDTO;
//import com.example.kok.dto.AdminInternNoticeListDTO;
//import com.example.kok.util.AdminExperienceCriteria;
//import com.example.kok.util.AdminExperienceListCriteria;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class AdminEmployTests {
//    @Autowired
//    private AdminEmployMapper adminEmployMapper;
//
////    Mapper
//    @Test
//    public void testSelectEmployAll(){
//        String keyword = "";
//        AdminExperienceListCriteria criteria = new AdminExperienceListCriteria(1, adminEmployMapper.selectEmploySearchCountAll(keyword));
//        adminEmployMapper.selectEmployAll(criteria, keyword).stream().map(AdminInternNoticeListDTO::toString).forEach(log::info);
//        log.info("전체 개수: {}", adminEmployMapper.selectEmploySearchCountAll(keyword));
//        log.info("criteria: {}", criteria);
//    }
//
//    @Test
//    public void testSelectEmploySearchCountAll(){
//        String keyword = "";
//        log.info("전체 개수: {}", adminEmployMapper.selectEmploySearchCountAll(keyword));
//    }
//
//    @Test
//    public void testSelectEmployById(){
//        Long id = 1L;
//        log.info("1번 게시글: {}", adminEmployMapper.selectEmployById(id));
//    }
//
//    @Test
//    public void testSelectRequestUser(){
//        Long id = 16L;
//        AdminExperienceCriteria criteria = new AdminExperienceCriteria(3, adminEmployMapper.countRequestUser(id));
//        adminEmployMapper.selectRequestUser(criteria, id).stream().map(AdminInternNoticeDetailRequestDTO::toString).forEach(log::info);
//    }
//
//}
