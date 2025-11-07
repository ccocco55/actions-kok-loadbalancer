//package com.example.kok.mapper;
//
//import com.example.kok.service.FollowService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class FollowTests {
//    @Autowired
//    private FollowService followService;
//    @Autowired
//    private FollowMapper followMapper;
//
//    private Long testMemberId = 1L;
//    private Long testCompanyId = 7L;
//
//    @Test
//    public void followInsertTest() {
//        followService.follow(testMemberId, testCompanyId);
//        boolean isFollowing = followService.isFollowing(testMemberId, testCompanyId);
//        log.info("팔로우 여부 : {}", isFollowing);
//    }
//
//    @Test
//    public void followDeleteTest() {
//        followService.follow(testMemberId, testCompanyId);
//        followService.unfollow(testMemberId, testCompanyId);
//        boolean isFollowing = followService.isFollowing(testMemberId, testCompanyId);
//        log.info("팔로우 여부 : {}", isFollowing);
//    }
//}
