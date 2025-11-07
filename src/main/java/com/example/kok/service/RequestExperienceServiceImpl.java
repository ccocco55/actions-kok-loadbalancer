package com.example.kok.service;

import com.example.kok.dto.PaymentDTO;
import com.example.kok.dto.PaymentUserDTO;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.dto.RequestExperienceFileDTO;
import com.example.kok.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestExperienceServiceImpl implements RequestExperienceService {
    private final RequestExperienceDAO requestExperienceDAO;
    private final RequestExperienceFileDAO requestExperienceFileDAO;
    private final MemberAlarmSettingDAO memberAlarmSettingDAO;
    private final PaymentDAO paymentDAO;
    private final PaymentUserDAO paymentUserDAO;

    @Override
    @Transactional
    public void applyForExperience(RequestExperienceDTO requestExperienceDTO) {
        Long memberAlarmSettingId=memberAlarmSettingDAO.findByMemberId(requestExperienceDTO.getMemberId());
        requestExperienceDTO.setMemberAlarmSettingId(memberAlarmSettingId);
        requestExperienceDAO.applyForExperience(requestExperienceDTO);
        System.out.println("생성된 ID: " + requestExperienceDTO.getId());
//        System.out.println(requestExperienceDTO.getId());
        Long reqId=requestExperienceDTO.getId();
        Long fileId=requestExperienceDTO.getFileId();
        RequestExperienceFileDTO file=new RequestExperienceFileDTO();
        file.setFileId(fileId);
        file.setRequestExperienceId(reqId);

        PaymentDTO payment=new PaymentDTO();
        payment.setPaymentPrice(2000);
        payment.setUserId(requestExperienceDTO.getMemberId());
//        System.out.println(reqId);
        payment.setRequestExperienceId(reqId);
        paymentDAO.payRequestExp(payment);

        PaymentUserDTO paymentUser=new PaymentUserDTO();
        paymentUser.setRequestExperienceId(reqId);
        paymentUser.setPaymentId(payment.getId());
        paymentUser.setUserId(requestExperienceDTO.getMemberId());
        paymentUserDAO.payRequestUserExp(paymentUser);
        requestExperienceFileDAO.saveRequestFile(file);
    }

    @Override
    public String getFileName(Long memberId) {
        return requestExperienceFileDAO.findFileNameByMemberId(memberId);
    }
}
