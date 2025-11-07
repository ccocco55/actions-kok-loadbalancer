package com.example.kok.repository;

import com.example.kok.dto.BannerFileDTO;
import com.example.kok.mapper.AdminBannerMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdminBannerDAO {
    private final AdminBannerMapper adminBannerMapper;

//    파일 추가
    public void insertFile (BannerFileDTO bannerFileDTO){
        adminBannerMapper.insertBannerFile(bannerFileDTO);
    }

//    파일 목록 조회
    public List<BannerFileDTO> selectAllFile(Criteria criteria){
        return adminBannerMapper.selectBannerFiles(criteria);
    }
    public List<BannerFileDTO> selectAllFileById(Long id){
        return adminBannerMapper.selectBannerFilesById(id);
    }

//    파일 전체 개수
    public int countAll (){
        return adminBannerMapper.countBannerFiles();
    }

//    파일 삭제
    public void deleteFile(Long id){
        adminBannerMapper.deleteBannerFile(id);
    }
}
