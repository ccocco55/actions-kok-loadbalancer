package com.example.kok.repository;

import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.dto.MemberAlarmSettingDTO;
import com.example.kok.dto.UserDTO;
import com.example.kok.mapper.MemberAlarmSettingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberAlarmSettingDAO {
    private final MemberAlarmSettingMapper memberAlarmSettingMapper;

    public void save(Long memberId){
        memberAlarmSettingMapper.insertByMemberId(memberId);
    };

//    멤버id로 알람id 조회
    public long findByMemberId(Long memberId){
        return memberAlarmSettingMapper.selectByMemberId(memberId);
    }
//    멤버 id로 전체 알람 조회
    public MemberAlarmSettingDTO findAllByMemberId(Long memberId){
        return memberAlarmSettingMapper.selectAllByMemberId(memberId);
    }
//    멤버 알람 활성화
    public void updateByKeywordToActive(Long id, String keyword){
         memberAlarmSettingMapper.updateByKeywordToActive(id,keyword);
    }
//    멤버 알람 비활성화
    public void updateByKeywordToInactive(Long id, String keyword){
        memberAlarmSettingMapper.updateByKeywordToInactive(id,keyword);
    }
    public List<UserDTO> findEmailForExperienceSendMail(Long id){
        return memberAlarmSettingMapper.selectByNoticeId(id);
    }
    public List<UserDTO> findEmailForInternSendMail(Long id){
        return memberAlarmSettingMapper.selectByNoticeId(id);
    }


}
