package com.example.kok.repository;

import com.example.kok.dto.ConsoleExperienceMemberDTO;
import com.example.kok.mapper.ConsoleMainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleMainDAO {
    private final ConsoleMainMapper consoleMainMapper;

//    활성화된 체험 공고 수
    public int findActiveExperienceNoticeCount(Long companyId) {
        return consoleMainMapper.countActiveExperienceNotice(companyId);
    }

//    전체 체험 공고 수
    public int findAllExperienceNoticeCount(Long companyId) {
        return consoleMainMapper.countAllExperienceNotice(companyId);
    }

    public int findActiveInternNoticeCount(Long companyId) {
        return consoleMainMapper.countActiveInternNotice(companyId);
    }

    public int findAllInternNoticeCount(Long companyId) {
        return consoleMainMapper.countAllInternNotice(companyId);
    }

//    지원한 회원 목록
    public List<ConsoleExperienceMemberDTO> findRecentActiveExperienceMembers(Long companyId) {
        return consoleMainMapper.selectRecentActiveExperienceMembers(companyId);
    }
}
