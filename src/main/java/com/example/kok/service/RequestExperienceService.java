package com.example.kok.service;

import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.dto.RequestExperienceFileDTO;

import java.util.List;

public interface RequestExperienceService {
//    지원서 파일 insert
    public void applyForExperience(RequestExperienceDTO requestExperienceDTO);

//    지원서 파일 이름 목록
    public String getFileName(Long memberId);
}
