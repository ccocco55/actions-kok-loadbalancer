package com.example.kok.mapper;

import com.example.kok.dto.RequestExperienceFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequestExperienceFileMapper {
//    지원서 파일 insert
    public void insertRequestFile(RequestExperienceFileDTO requestExperienceFileDTO);

//    지원서 파일 이름 목록
    public String selectFileNameByMemberId(Long memberId);
}
