package com.example.kok.repository;

import com.example.kok.dto.BannerFileDTO;
import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.mapper.BannerFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BannerFileDAO {
    private final BannerFileMapper bannerFileMapper;

//    가장 최근 배너 조회
    public Optional<BannerFileDTO> getBannerFileDTO() {
        return bannerFileMapper.selectLastBanner();
    }
}
