package com.example.kok.mapper;

import com.example.kok.dto.ConsoleProfileFileDTO;
import com.example.kok.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleProfileBackgroundFileMapper {
//    ID로 파일 목록 조회
    List<FileDTO> selectBackgroundFileByCompanyId(@Param("companyId") Long companyId);

//    파일 등록
    public void insertFile(FileDTO fileDTO);

//    파일 연결
    public void insertBackgroundFile(ConsoleProfileFileDTO consoleFileDTO);

//    파일 삭제
    public void deleteFilesByProfileId(@Param("companyId") Long companyId);

//    파일 자체 삭제
    public void deleteFileByCompanyId(@Param("companyId") Long companyId);

}
