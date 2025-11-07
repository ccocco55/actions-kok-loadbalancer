package com.example.kok.mapper;

import com.example.kok.domain.CompanyLicenseFileVO;
import com.example.kok.dto.CompanyLicenseFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyLicenseFileMapper {
    public void insertCompanyLicenseFile(CompanyLicenseFileDTO companyLicenseFileDTO);
}
