//package com.example.kok.mapper;
//
//import com.example.kok.dto.MemberDTO;
//import com.example.kok.dto.UserMemberDTO;
//import com.example.kok.repository.MemberDAO;
//import com.example.kok.service.MemberService;
//import com.example.kok.util.Criteria;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@Slf4j
//public class MemberMapperTests {
//
//    @Autowired
//    private MemberMapper memberMapper;
//    @Autowired
//    private MemberDAO memberDAO;
//    @Autowired
//    private MemberService memberService;
//
////    멤버 전체 조회
//
//    @Test
//    public void selectAllMembersTest(){
//        Criteria criteria = new Criteria(1, 10);
//        criteria.setOffset(0);
//        criteria.setCount(10);
//        List<UserMemberDTO> memberDTOs = memberMapper.selectMembers(criteria, "");
//
//        log.info("memberDTOs: {}", memberDTOs);
//    }
//
//    @Test
//    public void selectAllDAOMembersTest(){
//        Criteria criteria = new Criteria(1, 10);
//        List<UserMemberDTO> memberDTOs = memberDAO.selectMembers(criteria, "차");
//
//        log.info("memberDTOs: {}", memberDTOs);
//    }
//    @Test
//    public void selectMemberByIdTest(){
//        Criteria criteria = new Criteria(1, 10);
//        log.info("selectMembers: {}", memberService.findUserMembers(1, ""));
//    }
//
//    //    목록 개수 조회
//    @Test
//    public void countMembersTest(){
//        memberMapper.selectCount("차");
//        log.info("{}", memberMapper.selectCount("차"));
//    }
//
//    @Test
//    public void countMembersDAOTest(){
//        memberDAO.countMembers("차");
//        log.info("개수 :{}", memberDAO.countMembers(""));
//    }
//
//    //    아이디로 회원 조회
//    @Test
//    public void selectMemberTest(){
//        Optional<UserMemberDTO> memberDTOs = memberMapper.selectMember(1L);
//
//        log.info("memberDTOs: {}", memberDTOs);
//    }
//
//    @Test
//    public void selectDAOMemberTest(){
//        Optional<UserMemberDTO> memberDTOs = memberDAO.selectMember(1L);
//
//        log.info("memberDTOs: {}", memberDTOs);
//    }
//
//    @Test
//    public void selectAllMembersServiceTest(){
//        memberService.findMembersByMemberId(1L);
//        log.info("memberDTOs: {}", memberService.findMembersByMemberId(1L));
//    }
//
//    @Test
//    public void selectRequestTest(){
//        System.out.println(memberMapper.selectExperienceByMemberId(16L));
//        System.out.println(memberMapper.selectInternByMemberId(16L));
//    }
//
//    @Test
//    public void requestTest(){
//        System.out.println(memberService.findRequestExperienceByMemberId(16L));
//        System.out.println(memberService.findRequestInternByMemberId(16L));
//    }
//
//    @Test
//    public void requestInternTest(){
//        System.out.println(memberMapper.selectInternByMemberId(16L));
//        System.out.println(memberService.findRequestInternByMemberId(16L));
//    }
//
//    @Test
//    public void postsTest(){
//        System.out.println(memberMapper.selectPostsByMemberId(16L));
//    }
//
//    @Test
//    public void paymentTest(){
//        System.out.println(memberMapper.selectPaymentByMemberId(16L));
//        System.out.println(memberService.findPaymentByMemberId(16L));
//    }
//
//    @Test
//    public void profileUpdate(){
////        memberDAO.plusJob(16L, "기획/전략");
//        System.out.println(memberDAO.findJobCategoryByMemberId(16L));
//    }
//
//    @Test
//    public void infoUpdate(){
////        memberMapper.updateProfileInfo(23L, "sdfg");
//        memberDAO.updateInfo(23L, "ㄴㅇㄹ호");
//    }
//}
