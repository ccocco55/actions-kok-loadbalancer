package com.example.kok.mapper;

import com.example.kok.dto.ConsoleProfileFileDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.PostFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ConsoleProfileFileMapper {
//    ID로 파일 목록 조회
    List<FileDTO> selectProfileFileByCompanyId(@Param("companyId") Long companyId);

//    파일 등록
    public void insertFile(FileDTO fileDTO);

//    파일 연결
    public void insertProfileFile(ConsoleProfileFileDTO consoleFileDTO);

//    파일 삭제
    public void deleteFilesByProfileId(@Param("companyId") Long companyId);

//    파일 단독 삭제
    public void deleteFileByCompanyId(@Param("companyId") Long companyId);

}
