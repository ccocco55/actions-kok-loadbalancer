package com.example.kok.service;

import com.example.kok.dto.RequestInternDTO;
import com.example.kok.dto.RequestInternFileDTO;
import com.example.kok.dto.UserMemberDTO;
import com.example.kok.repository.MemberAlarmSettingDAO;
import com.example.kok.repository.MemberDAO;
import com.example.kok.repository.RequestInternDAO;
import com.example.kok.repository.RequestInternFileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestInternServiceImpl implements RequestInternService {
    private final RequestInternDAO requestInternDAO;
    private final RequestInternFileDAO requestInternFileDAO;
    private final MemberAlarmSettingDAO memberAlarmSettingDAO;
    private final MemberDAO memberDAO;

    @Override
    @Transactional
    public void applyForIntern(RequestInternDTO requestInternDTO) {
        Long memberAlarmSettingId=memberAlarmSettingDAO.findByMemberId(requestInternDTO.getMemberId());
        requestInternDTO.setMemberAlarmSettingId(memberAlarmSettingId);
        requestInternDAO.applyForIntern(requestInternDTO);
        System.out.println(requestInternDTO.getId());
//        Optional<UserMemberDTO> member=memberDAO.selectMember(requestInternDTO.getMemberId());
        Long reqId=requestInternDTO.getId();
        Long fileId=requestInternDTO.getFileId();
        RequestInternFileDTO file=new RequestInternFileDTO();
        file.setFileId(fileId);
        file.setRequestInternId(reqId);
        requestInternFileDAO.saveRequestFile(file);
    }

    @Override
    public boolean isRequested(RequestInternDTO requestInternDTO) {
//        System.out.println("서비스 임플: "+requestInternDAO.isRequested(requestInternDTO));
        boolean result=requestInternDAO.isRequested(requestInternDTO);
        return result;
    }
}
