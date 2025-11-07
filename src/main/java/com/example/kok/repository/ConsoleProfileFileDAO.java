
package com.example.kok.repository;

import com.example.kok.dto.CompanyProfileFileDTO;
import com.example.kok.dto.ConsoleProfileFileDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.PostFileDTO;
import com.example.kok.mapper.ConsoleProfileFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConsoleProfileFileDAO {
    private final ConsoleProfileFileMapper fileMapper;

//    목록 조회
    public List<FileDTO> findAllByProfileId(Long companyId) {
        return fileMapper.selectProfileFileByCompanyId(companyId);
    }

//    파일 등록
    public void saveFile(FileDTO fileDTO) {
        fileMapper.insertFile(fileDTO);
    }

//    파일 연결
    public void linkFileToProfile(ConsoleProfileFileDTO consoleFileDTO) {
        fileMapper.insertProfileFile(consoleFileDTO);
    }

    public void deleteAllFilesByProfileId(Long companyId) {
        // 파일 연결 삭제
        fileMapper.deleteFilesByProfileId(companyId);

        // 파일 삭제
        fileMapper.deleteFileByCompanyId(companyId);
    }

}
