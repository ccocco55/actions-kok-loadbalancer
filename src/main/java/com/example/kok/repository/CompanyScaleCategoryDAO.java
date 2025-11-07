package com.example.kok.repository;

import com.example.kok.dto.CompanyScaleCategoryBridgeDTO;
import com.example.kok.mapper.CompanyScaleCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanyScaleCategoryDAO {
    private final CompanyScaleCategoryMapper companyScaleCategoryMapper;
    public void insertScale(CompanyScaleCategoryBridgeDTO companyScaleCategoryBridgeDTO){companyScaleCategoryMapper.insertScale(companyScaleCategoryBridgeDTO);};
}
