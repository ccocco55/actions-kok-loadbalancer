//package com.example.kok.mapper;
//
//import com.example.kok.dto.SaveInternNoticeDTO;
//import com.example.kok.repository.InternNoticeDAO;
//import com.example.kok.repository.SaveInternNoticeDAO;
//import com.example.kok.service.InternNoticeService;
//import com.example.kok.util.Criteria;
//import com.example.kok.util.Search;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class InternTests {
//    @Autowired
//    private InternNoticeDAO internNoticeDAO;
//    @Autowired
//    private InternNoticeService internNoticeService;
//    @Autowired
//    private InternNoticeMapper internNoticeMapper;
//    @Autowired
//    private SaveInternNoticeDAO saveInternNoticeDAO;
//
//    @Test
//    public void listTest(){
//        Search search = new Search();
//        Criteria criteria=new Criteria(1, 10);
//        System.out.println("################################");
//        System.out.println(internNoticeMapper.selectAllInternNotice(criteria, search));
//        System.out.println("################################");
//        System.out.println(internNoticeDAO.findAll(criteria, search));
//        System.out.println("################################");
//        System.out.println(internNoticeService.selectAllInternNotice(1, search).toString());
//    }
//
//    @Test
//    public void isSavedTest(){
//        long memberId=16;
//        long internNoticeId=1;
//        SaveInternNoticeDTO interns=new SaveInternNoticeDTO();
//        interns.setInternNoticeId(internNoticeId);
//        interns.setMemberId(memberId);
//        boolean result= internNoticeService.isSavedInt(interns);
//        System.out.println("################################");
//        System.out.println(result);
//        saveInternNoticeDAO.saveInt(interns);
//        result= internNoticeService.isSavedInt(interns);
//        System.out.println("################################");
//        System.out.println(result);
//        saveInternNoticeDAO.deleteInt(interns);
//        result= internNoticeService.isSavedInt(interns);
//        System.out.println("################################");
//        System.out.println(result);
//    }
//}
