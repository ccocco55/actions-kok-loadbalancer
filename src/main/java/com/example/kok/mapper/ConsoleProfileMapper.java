package com.example.kok.mapper;

import com.example.kok.domain.ConsoleCompanyProfileVO;
import com.example.kok.dto.ConsoleCompanyProfileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConsoleProfileMapper {
    // 기업 프로필
    ConsoleCompanyProfileDTO selectCompanyProfileByUserId(@Param("companyId") Long companyId);

    // 기업 프로필 수정
    void updateCompanyProfile(ConsoleCompanyProfileVO profileVO);

    // 대표자명 수정
    void updateCeoName(ConsoleCompanyProfileVO profileVO);

    // 산업 분야 수정
    void updateCompanySector(ConsoleCompanyProfileVO profileVO);

    // 기업 규모 수정
    void updateCompanyScale(ConsoleCompanyProfileVO profileVO);
}
