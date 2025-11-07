//package com.example.kok.mapper;
//
//import com.example.kok.dto.ExperienceNoticeDTO;
//import com.example.kok.dto.UserDTO;
//import com.example.kok.repository.ExperienceNoticeDAO;
//import com.example.kok.service.MailService;
//import com.example.kok.service.SmsService;
//import jakarta.mail.MessagingException;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//public class SmsTest {
//    @Autowired
//    private SmsService smsService;
//    @Autowired
//    private MemberAlarmSettingMapper memberAlarmSettingMapper;
//    @Autowired
//    private MailService mailService;
//    @Autowired
//    private ExperienceNoticeDAO experienceNoticeDAO;
//
//    @Test
//    public void sendSms(){
//        String phone ="01051133095";
//        smsService.send(phone);
//    }
//    @Test
//    public void sendAlarm(){
//        List<UserDTO> userDTO = memberAlarmSettingMapper.selectByNoticeId(4L);
//        log.info(userDTO.toString());
//        ExperienceNoticeDTO experienceNoticeDTO= experienceNoticeDAO.findCompanyNameById(4L);
//        if(userDTO!=null){
//            userDTO.forEach((user)->{
//            if(user.getUserEmail()!=null) {
//                try {
//                    mailService.sendMailByNotice(user.getUserEmail(),user.getUserName(),experienceNoticeDTO.getExperienceNoticeTitle(),experienceNoticeDTO.getCompanyName());
//                } catch (MessagingException e) {
//                    throw new RuntimeException(e);
//                }
//            }else{
//                try {
//                    mailService.sendMailByNotice(user.getSnsEmail(),user.getUserName(),experienceNoticeDTO.getExperienceNoticeTitle(),experienceNoticeDTO.getCompanyName());
//                } catch (MessagingException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });}
//
//
//    }
//}
