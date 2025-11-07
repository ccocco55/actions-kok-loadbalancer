//package com.example.kok.mapper;
//
//import com.example.kok.repository.InternNoticeDAO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class InternNoticeMapperTests {
//    @Autowired
//    InternNoticeMapper internNoticeMapper;
//
//    @Autowired
//    InternNoticeDAO internNoticeDAO;
//
//    @Test
//    public  void selectListByIdTest()
//    {
//        log.info("selectListByIdTest: {}", internNoticeMapper.selectListById(6L));
//    }
//
//    @Test
//    public  void selectListByIdDAOTest()
//    {
//        log.info("selectListByIdTest: {}", internNoticeDAO.findInternNotices(6L));
//    }
//}
