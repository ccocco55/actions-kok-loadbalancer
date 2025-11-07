package com.example.kok.repository;

import com.example.kok.dto.FileDTO;
import com.example.kok.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

//    파일 insert
    public void saveFile(FileDTO fileDTO) {
        fileMapper.insertFile(fileDTO);
    }

//    기업 id로 프사 조회
    public Optional<FileDTO> findFileByCompanyId(Long userId) {
        return fileMapper.selectFileByCompanyId(userId);
    }

//    파일 uuid 합친 이름으로 id 조회
    public Long findFileIdByName(String fileName) {
        return fileMapper.selectFileByName(fileName);
    }

//    id로 프사 조회
    public FileDTO findFileById(Long id) {
        return fileMapper.selectProfileById(id);
    }
}
