package com.example.kok.mapper;

import com.example.kok.dto.CompanyScaleCategoryBridgeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyScaleCategoryMapper {
    public void insertScale (CompanyScaleCategoryBridgeDTO  companyScaleCategoryBridgeDTO);
}
