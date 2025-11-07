package com.example.kok.mapper;

import com.example.kok.dto.ConsoleAdNoticeFileDTO;
import com.example.kok.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleAdFileMapper {
//    광고 ID로 파일 목록 조회
    List<FileDTO> selectAdBackgroundFileByAdId(@Param("advertisementId") Long advertisementId);

//    파일 등록
    public void insertFile(FileDTO fileDTO);

//    광고 파일 연결
    public void insertAdBackgroundFile(ConsoleAdNoticeFileDTO consoleFileDTO);

//    광고 파일 삭제
    public void deleteFilesByAdId(@Param("advertisementId") Long advertisementId);

//    파일 자체 삭제
    public void deleteFileByAdvertisementId(@Param("advertisementId") Long advertisementId);

}
