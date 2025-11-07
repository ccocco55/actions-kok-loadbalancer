package com.example.kok.mapper;

import com.example.kok.dto.CompanyScaleCategoryBridgeDTO;
import com.example.kok.dto.CompanySectorCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanySectorCategoryMapper {
    public void insertSector(CompanySectorCategoryDTO companySectorCategoryDTO);
}
