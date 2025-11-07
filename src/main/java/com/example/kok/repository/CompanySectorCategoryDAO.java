package com.example.kok.repository;

import com.example.kok.dto.CompanyScaleCategoryBridgeDTO;
import com.example.kok.dto.CompanySectorCategoryDTO;
import com.example.kok.mapper.CompanyScaleCategoryMapper;
import com.example.kok.mapper.CompanySectorCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanySectorCategoryDAO {
    private final CompanySectorCategoryMapper companySectorCategoryMapper;
    public void insertSector(CompanySectorCategoryDTO companySectorCategoryDTO){companySectorCategoryMapper.insertSector(companySectorCategoryDTO);};
}
