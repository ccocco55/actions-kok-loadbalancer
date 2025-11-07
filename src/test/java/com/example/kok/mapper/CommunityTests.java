//package com.example.kok.mapper;
//
//import com.example.kok.dto.PostDTO;
//import com.example.kok.repository.CommunityPostDAO;
//import com.example.kok.service.CommunityPostService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class CommunityTests {
//    @Autowired
//    private CommunityPostMapper communityPostMapper;
//    @Autowired
//    private CommunityPostDAO communityPostDAO;
//    @Autowired
//    private CommunityPostService communityPostService;
//
//    @Test
//    public void testInsertPost() {
//        PostDTO postDTO = new PostDTO();
//        postDTO.setPostContent("test");
//        log.info("{}", postDTO);
//    }
//
////    회원별 게시물 조회
//
//    @Test
//    public void testSelectPostById() {
//        log.info("{}", communityPostMapper.selectPostById(1L));
//    }
//
//    @Test
//    public void testSelectPostByIds() {
//        log.info("{}", communityPostDAO.findPostById(1L));
//    }
//}
