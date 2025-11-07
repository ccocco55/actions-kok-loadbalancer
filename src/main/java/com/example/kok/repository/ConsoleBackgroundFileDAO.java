
package com.example.kok.repository;

import com.example.kok.dto.ConsoleProfileFileDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.mapper.ConsoleProfileBackgroundFileMapper;
import com.example.kok.mapper.ConsoleProfileFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleBackgroundFileDAO {
    private final ConsoleProfileBackgroundFileMapper fileMapper;

    // 광고 ID로 파일 목록 조회
    public List<FileDTO> findAllByProfileId(Long companyId) {
        return fileMapper.selectBackgroundFileByCompanyId(companyId);
    }

    // 파일 등록
    public void saveFile(FileDTO fileDTO) {
        fileMapper.insertFile(fileDTO);
    }

    // 광고-파일 연결
    public void linkFileToProfile(ConsoleProfileFileDTO consoleFileDTO) {
        fileMapper.insertBackgroundFile(consoleFileDTO);
    }

    public void deleteAllFilesByProfileId(Long companyId) {
        // 광고파일 연결 삭제
        fileMapper.deleteFilesByProfileId(companyId);

        // 파일 삭제
        fileMapper.deleteFileByCompanyId(companyId);
    }

}
