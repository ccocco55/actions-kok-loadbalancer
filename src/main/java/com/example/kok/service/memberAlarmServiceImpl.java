package com.example.kok.service;

import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.dto.UserDTO;
import com.example.kok.mapper.MemberAlarmSettingMapper;
import com.example.kok.repository.ExperienceNoticeDAO;
import com.example.kok.repository.InternNoticeDAO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class memberAlarmServiceImpl implements memberAlarmService {
    private final MemberAlarmSettingMapper memberAlarmSettingMapper;
    private final ExperienceNoticeDAO  experienceNoticeDAO;
    private final InternNoticeDAO internNoticeDAO;
    private final MailService mailService;

    @Override
    public void experienceAlarm(Long id) {
        List<UserDTO> userDTO = memberAlarmSettingMapper.selectByNoticeId(id);
        ExperienceNoticeDTO experienceNoticeDTO= experienceNoticeDAO.findCompanyNameById(id);
        if(userDTO!=null){
            userDTO.forEach((user)->{
                if(user.getUserEmail()!=null) {
                    try {
                        mailService.sendMailByNotice(user.getUserEmail(),user.getUserName(),experienceNoticeDTO.getExperienceNoticeTitle(),experienceNoticeDTO.getCompanyName());
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    try {
                        mailService.sendMailByNotice(user.getSnsEmail(),user.getUserName(),experienceNoticeDTO.getExperienceNoticeTitle(),experienceNoticeDTO.getCompanyName());
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });}
    }

    @Override
    public void internAlarm(Long id) {
        List<UserDTO> userDTO = memberAlarmSettingMapper.selectByInturnNoticeId(id);
        InternNoticeDTO internNoticeDTO = internNoticeDAO.findCompanyNameById(id);
        if(userDTO!=null){
            userDTO.forEach((user)->{
                if(user.getUserEmail()!=null) {
                    try {
                        mailService.sendMailByNotice(user.getUserEmail(),user.getUserName(),internNoticeDTO.getInternNoticeTitle(),internNoticeDTO.getCompanyName());
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    try {
                        mailService.sendMailByNotice(user.getSnsEmail(),user.getUserName(),internNoticeDTO.getInternNoticeTitle(),internNoticeDTO.getCompanyName());
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });}

    }
}
