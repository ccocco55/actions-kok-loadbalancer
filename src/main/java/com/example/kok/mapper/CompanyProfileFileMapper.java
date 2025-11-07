package com.example.kok.mapper;

import com.example.kok.dto.CompanyProfileFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyProfileFileMapper {
//    기업 id로 프로필 사진 조회
    public CompanyProfileFileDTO selectByCompanyId(Long companyId);
    public int selectCountByCompanyId(Long companyId);
}
