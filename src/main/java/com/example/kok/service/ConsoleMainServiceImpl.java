package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.*;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsoleMainServiceImpl implements ConsoleMainService {
    private final ConsoleMainDAO consoleMainDAO;

    // 활성화된 체험 공고 수
    @Override
    public int getActiveExperienceNoticeCount(Long companyId) {
        return consoleMainDAO.findActiveExperienceNoticeCount(companyId);
    }

    // 전체 체험 공고 수
    @Override
    public int getAllExperienceNoticeCount(Long companyId) {
        return consoleMainDAO.findAllExperienceNoticeCount(companyId);
    }

    @Override
    public int getActiveInternNoticeCount(Long companyId) {
        return consoleMainDAO.findActiveInternNoticeCount(companyId);
    }

    @Override
    public int getAllInternNoticeCount(Long companyId) {
        return consoleMainDAO.findAllInternNoticeCount(companyId);
    }

    // 지원한 회원 목록
    @Override
    public List<ConsoleExperienceMemberDTO> getRecentExperienceMembers(Long companyId) {
        return consoleMainDAO.findRecentActiveExperienceMembers(companyId);
    }

}
