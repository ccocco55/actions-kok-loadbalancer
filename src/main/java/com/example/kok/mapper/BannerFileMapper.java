package com.example.kok.mapper;

import com.example.kok.dto.BannerFileDTO;
import com.example.kok.dto.RequestExperienceDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface BannerFileMapper {
//    가장 최근 배너 조회
    public Optional<BannerFileDTO> selectLastBanner();
}
