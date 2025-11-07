//package com.example.kok.mapper;
//
//import com.example.kok.repository.RequestExperienceDAO;
//import com.example.kok.repository.RequestInternDAO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class RequestInternMapperTests {
//
//    @Autowired
//    private RequestInternMapper requestInternMapper;
//    @Autowired
//    private RequestInternDAO requestInternDAO;
//
//    @Test
//    public void testFindInternById() {
//        requestInternMapper.selectRequestInternById(1L);
//        log.info("selectRequestInternById: {}",  requestInternMapper.selectRequestInternById(1L));
//    }
//
//    @Test
//    public void testFindInternDAOById() {
//        requestInternDAO.selectAllInternById(1L);
//        log.info("selectRequestInternById: {}",  requestInternDAO.selectAllInternById(1L));
//    }
//}
