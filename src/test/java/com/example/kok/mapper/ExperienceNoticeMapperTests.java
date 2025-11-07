//package com.example.kok.mapper;
//
//import com.example.kok.dto.ExperienceNoticeDTO;
//import com.example.kok.repository.ExperienceNoticeDAO;
//import com.example.kok.service.ConsoleExperienceApplicationService;
//import com.example.kok.service.FileService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//
//@SpringBootTest
//@Slf4j
//public class ExperienceNoticeMapperTests {
//    @Autowired
//    ExperienceNoticeMapper experienceNoticeMapper;
//    @Autowired
//    ExperienceNoticeDAO experienceNoticeDAO;
//    @Autowired
//    ConsoleExperienceApplicationService consoleExperienceApplicationService;
//    @Autowired
//    FileService fileService;
//
//    @Test
//    public void testFindCompanyExperienceNotice() {
//        log.info("testFindCompanyExperienceNotice: {}", experienceNoticeMapper.selectListById(6L));
//    }
//
//    @Test
//    public void daoTestFindCompanyExperienceNotice() {
//        log.info("result: {}", experienceNoticeDAO.selectListById(6L));
//    }
//
//    @Test
//    public void daoTestFindLatestFour() {
//        log.info("result: {}", experienceNoticeMapper.companyExperienceCount(6L));
//    }
//
//
//    @Test
//    public void reviewTest(){
//        ExperienceNoticeDTO result= experienceNoticeDAO.findById(24L);
//        System.out.println(result);
//        String jobName= experienceNoticeDAO.findJobNameByID(24L);
//        result.setJobName(jobName);
//        System.out.println(result);
//        LocalDate endDate = result.getExperienceNoticeEndDate();
//            LocalDate today = LocalDate.now();
//            if (!endDate.isBefore(today)) {
//                long days = ChronoUnit.DAYS.between(today, endDate);
//                result.setRemainingDays(days);
//            } else {
//                result.setRemainingDays(0L); // endDate보다 today가 이전일 경우 0
//            }
//            fileService.findFileByCompanyId(result.getCompanyId())
//                    .ifPresentOrElse(fileDTO -> {
//                        result.setFileName(fileDTO.getFileName());
//                        result.setFilePath(fileDTO.getFilePath());
//                    }, ()->{
//                        result.setFileName("image.png");
//                        result.setFilePath("");
//                    });
//        System.out.println("result: "+result);
//    }
//}
