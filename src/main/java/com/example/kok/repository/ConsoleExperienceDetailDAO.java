package com.example.kok.repository;

import com.example.kok.dto.ConsoleExperienceApplicantDTO;
import com.example.kok.dto.ConsoleExperienceNoticeDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.mapper.ConsoleExperienceDetailMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleExperienceDetailDAO {
    private final ConsoleExperienceDetailMapper consoleExperienceMapper;

//    공고 상세 내용
    public ConsoleExperienceNoticeDTO findExperienceDetailByCompany(Long experienceNoticeId) {
        return consoleExperienceMapper.selectExperienceDatailByCompany(experienceNoticeId);
    }

//    지원 목록
    public List<ConsoleExperienceApplicantDTO> findAllByCompany(Long companyId, RequestStatus status, Criteria criteria) {
        return consoleExperienceMapper.selectExperienceApplicantByCompany(companyId, status, criteria);
    }

//    지원 목록 개수
    public int findCountByCompany(Long companyId, RequestStatus status) {
        return consoleExperienceMapper.selectExperienceApplicantCountByCompany(companyId, status);
    }

}
