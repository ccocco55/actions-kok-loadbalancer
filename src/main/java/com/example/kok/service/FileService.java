package com.example.kok.service;

import com.example.kok.dto.FileDTO;

import java.util.Optional;

public interface FileService {
//    기업 id로 프로필 사진 조회
    public Optional<FileDTO> findFileByCompanyId(Long userId);
}
