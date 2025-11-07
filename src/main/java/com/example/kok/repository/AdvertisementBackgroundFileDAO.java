package com.example.kok.repository;

import com.example.kok.dto.AdvertisementBackgroundFileDTO;
import com.example.kok.mapper.AdvertisementBackgroundFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdvertisementBackgroundFileDAO {
    private final AdvertisementBackgroundFileMapper advertisementBackgroundFileMapper;

//    광고 신청 파일 조회
    public List<AdvertisementBackgroundFileDTO> advertisementBackgroundFile(Long id) {
        return advertisementBackgroundFileMapper.selectAdvertisementBackgroundFileById(id);
    }

//    광고 신청 파일 경로 조회
    public Optional<AdvertisementBackgroundFileDTO> backgroundFilePath(Long id) {
        return advertisementBackgroundFileMapper.selectBackgroundFilePathById(id);
    }

//    파일 조회
    public Optional<AdvertisementBackgroundFileDTO> backgroundFile(Long id) {
        return advertisementBackgroundFileMapper.selectBackgroundFileById(id);
    }
}
