package com.example.kok.mapper;

import com.example.kok.dto.BannerFileDTO;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminBannerMapper {
//    파일 추가
    public void insertBannerFile (BannerFileDTO bannerFileDTO);

//    파일 목록 조회
    public List<BannerFileDTO> selectBannerFiles(Criteria criteria);
    public List<BannerFileDTO> selectBannerFilesById(Long id);

//    파일 전체 개수
    public int countBannerFiles();

//    파일 삭제
    public void deleteBannerFile(Long id);
}
