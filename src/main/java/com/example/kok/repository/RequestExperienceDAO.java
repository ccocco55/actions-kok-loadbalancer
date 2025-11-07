package com.example.kok.repository;

import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.mapper.RequestExperienceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestExperienceDAO {
    private final RequestExperienceMapper requestExperienceMapper;

    //    지원서 추가
    public void applyForExperience(RequestExperienceDTO requestExperienceDTO){
        requestExperienceMapper.insertRequest(requestExperienceDTO);
    }

    //    회원별 지원서 조회
    public List<RequestExperienceDTO> selectAllRequestById(Long id){
        return requestExperienceMapper.selectRequestById(id);
    }

    //    멤버 아이디로 체험 개수 조회
    public int  selectRequestCountById(Long id){
        return requestExperienceMapper.selectRequestCountById(id);
    }

    //    지원 내역 목록 조회
    public List<RequestExperienceDTO> selectAllRequestByUserId(Long id,Long experienceId){
        return requestExperienceMapper.selectRequestByUserId(id,experienceId);
    }
    //    지원 여부 판별
    public boolean isRequested(RequestExperienceDTO requestExperienceDTO){
        Long experienceNoticeId=requestExperienceDTO.getExperienceNoticeId();
        Long memberId = requestExperienceDTO.getMemberId();
        System.out.println("다오 experienceNoticeId:"+experienceNoticeId+" memberId:"+memberId);
        if(requestExperienceMapper.countRequest(experienceNoticeId, memberId)>0){
            return true;
        } else{
            return false;
        }
    }

    //    지원서 id 조회
    public Long findId(Long memberId, Long experienceId){
        return requestExperienceMapper.selectId(memberId, experienceId);
    }
}
