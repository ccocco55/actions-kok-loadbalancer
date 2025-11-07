package com.example.kok.mapper;

import com.example.kok.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface FileMapper {
//    파일 넣기
    public void insertFile(FileDTO fileDTO);
//    기업 아이디로 프사 조회
    public Optional<FileDTO> selectFileByCompanyId(Long userId);

//    fileName으로 id 조회
    public Long selectFileByName(String fileName);
//    id로 프로필 조회
    public FileDTO selectProfileById(Long id);
}
