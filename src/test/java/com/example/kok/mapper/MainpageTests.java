//
//package com.example.kok.mapper;
//
//import com.example.kok.dto.UserDTO;
//import com.example.kok.dto.UserMemberDTO;
//import com.example.kok.repository.*;
//import com.example.kok.service.MainpageService;
//import com.example.kok.service.MemberService;
//import com.example.kok.service.S3Service;
//import com.example.kok.util.Criteria;
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@Slf4j
//public class MainpageTests {
//    @Autowired
//    private S3Service s3Service;
//    @Autowired
//    private MemberMapper memberMapper;
//    @Autowired
//    private MemberDAO memberDAO;
//    @Autowired
//    private RequestExperienceDAO requestExperienceDAO;
//    @Autowired
//    private MemberAlarmSettingDAO  memberAlarmSettingDAO;
//    @Autowired
//    private FollowDAO followDAO;
//    @Autowired
//    private MainpageService mainpageService;
//
//    @Test
//    public void mainpageTest(){
//        if(followDAO.selectAllFollow()>0) {
//            Assertions.assertThat(mainpageService.findPopularCompanies().toString());
//        }
//    }
//
//
//}
