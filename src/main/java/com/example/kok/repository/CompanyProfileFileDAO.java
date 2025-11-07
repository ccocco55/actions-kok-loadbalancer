package com.example.kok.repository;

import com.example.kok.dto.CompanyProfileFileDTO;
import com.example.kok.mapper.CompanyProfileFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanyProfileFileDAO {
    private final CompanyProfileFileMapper companyProfileFileMapper;

    public CompanyProfileFileDTO findFileByCompanyId(Long companyId){
        return companyProfileFileMapper.selectByCompanyId(companyId);
    }
    public int findCountByCompanyId(Long companyId){
        return companyProfileFileMapper.selectCountByCompanyId(companyId);
    }
}
