package com.example.kok.mapper;

import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.dto.MemberAlarmSettingDTO;
import com.example.kok.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberAlarmSettingMapper {
    public void insertByMemberId(Long memberId);

//    멤버id로 알람id 조회
    public long selectByMemberId(Long memberId);
//    멤버 id로 멤버 알람 조회
    public MemberAlarmSettingDTO selectAllByMemberId(Long memberId);
//    멤버 알람 활성화
    public void updateByKeywordToActive(Long id, String keyword);
//    멤버 알람 비활성화
    public void updateByKeywordToInactive(Long id, String keyword);
//    멤버 체험 공고 알람 보내기 위한 아이디 조회
    public List<UserDTO> selectByNoticeId(Long od);
    public List<UserDTO> selectByInturnNoticeId(Long od);
}
