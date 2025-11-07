package com.example.kok.repository;

import com.example.kok.domain.CompanyLicenseFileVO;
import com.example.kok.dto.CompanyLicenseFileDTO;
import com.example.kok.mapper.CompanyLicenseFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanyLicenseFileDAO {
    private final CompanyLicenseFileMapper companyLicenseFileMapper;

    public void saveCompanyLicenseFile(CompanyLicenseFileDTO companyLicenseFileDTO){
        companyLicenseFileMapper.insertCompanyLicenseFile(companyLicenseFileDTO);
    }
}
