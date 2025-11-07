package com.example.kok.repository;

import com.example.kok.dto.RequestExperienceFileDTO;
import com.example.kok.mapper.RequestExperienceFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RequestExperienceFileDAO {
    private final RequestExperienceFileMapper requestExperienceFileMapper;
//    지원서 파일 insert
    public void saveRequestFile(RequestExperienceFileDTO requestExperienceFileDTO){
        requestExperienceFileMapper.insertRequestFile(requestExperienceFileDTO);
    }

//    지원서 파일 이름 목록
    public String findFileNameByMemberId(Long memberId){
        return requestExperienceFileMapper.selectFileNameByMemberId(memberId);
    }
}
