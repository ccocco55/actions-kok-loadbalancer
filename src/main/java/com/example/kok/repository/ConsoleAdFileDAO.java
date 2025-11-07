
package com.example.kok.repository;

import com.example.kok.dto.ConsoleAdNoticeFileDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.mapper.ConsoleAdFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleAdFileDAO {
    private final ConsoleAdFileMapper fileMapper;

    // 광고 ID로 파일 목록 조회
    public List<FileDTO> findAllByAdvertisementId(Long advertisementId) {
        return fileMapper.selectAdBackgroundFileByAdId(advertisementId);
    }

    // 파일 등록
    public void saveFile(FileDTO fileDTO) {
        fileMapper.insertFile(fileDTO);
    }

    // 광고-파일 연결
    public void linkFileToAdvertisement(ConsoleAdNoticeFileDTO consoleFileDTO) {
        fileMapper.insertAdBackgroundFile(consoleFileDTO);
    }

    // 광고-파일 연결 삭제
    public void deleteAdFileLinks(Long advertisementId) {
        fileMapper.deleteFilesByAdId(advertisementId);
    }

    // 실제 파일 데이터 삭제
    public void deleteFiles(Long advertisementId) {
        fileMapper.deleteFileByAdvertisementId(advertisementId);
    }

}
