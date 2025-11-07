package com.example.kok.repository;

import com.example.kok.domain.ConsoleCompanyProfileVO;
import com.example.kok.dto.ConsoleCompanyProfileDTO;
import com.example.kok.mapper.ConsoleProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConsoleProfileDAO {
    private final ConsoleProfileMapper consoleProfileMapper;

    // 기업 프로필
    public ConsoleCompanyProfileDTO findCompanyProfileByUserId(Long companyId) {
        return consoleProfileMapper.selectCompanyProfileByUserId(companyId);
    }

    // 기업 프로필 수정
    public void updateCompanyProfile(ConsoleCompanyProfileVO profileVO) {
        consoleProfileMapper.updateCompanyProfile(profileVO);
    }

    // 대표자명 수정
    public void updateCeoName(ConsoleCompanyProfileVO profileVO) {
        consoleProfileMapper.updateCeoName(profileVO);
    }

    // 산업 분야 수정
    public void updateCompanySector(ConsoleCompanyProfileVO profileVO) {
        consoleProfileMapper.updateCompanySector(profileVO);
    }

    // 기업 규모 수정
    public void updateCompanyScale(ConsoleCompanyProfileVO profileVO) {
        consoleProfileMapper.updateCompanyScale(profileVO);
    }
}
