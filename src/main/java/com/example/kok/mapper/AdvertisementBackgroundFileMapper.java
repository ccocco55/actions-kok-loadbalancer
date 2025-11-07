package com.example.kok.mapper;

import com.example.kok.dto.AdvertisementBackgroundFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdvertisementBackgroundFileMapper {

//    광고 신청 파일 조회
    public List<AdvertisementBackgroundFileDTO> selectAdvertisementBackgroundFileById(Long id);

//    광고 신청 파일 경로 조회
    public Optional<AdvertisementBackgroundFileDTO> selectBackgroundFilePathById(Long id);

//    파일 조회
    public Optional<AdvertisementBackgroundFileDTO> selectBackgroundFileById(Long id);
}
