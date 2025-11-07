package com.example.kok.mapper;

import com.example.kok.dto.RequestExperienceDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RequestExperienceMapper {
    //    지원서 넣기
    public void insertRequest(RequestExperienceDTO requestExperienceDTO);

    //    멤버별 지원서 조회
    public List<RequestExperienceDTO> selectRequestById(Long id);

    //   멤버 아이디로 체험 개수 조회
    public int selectRequestCountById(Long id);

    //    지원 내역 목록 조회
    public List<RequestExperienceDTO> selectRequestByUserId(@Param("id") Long id,@Param("experienceId") Long experienceId);

    // 멤버 id와 공고 id로 지원서 개수 가져오기
    public int countRequest(Long experienceNoticeId, Long memberId);

    //    지원서 조회
    public Long selectId(Long memberId, Long experienceId);
}
