package com.example.kok.service;

import com.example.kok.dto.BannerFileCriteriaDTO;
import com.example.kok.dto.BannerFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminBannerService {
//    파일 추가
    public void save(List<MultipartFile> multipartFiles);

//    파일 목록 조회
    public BannerFileCriteriaDTO bannerList(int page);

//    파일 삭제
    public void delete(Long id);
}
