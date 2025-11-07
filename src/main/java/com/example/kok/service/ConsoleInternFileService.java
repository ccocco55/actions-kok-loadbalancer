package com.example.kok.service;

import com.example.kok.dto.FileDTO;
import com.example.kok.repository.ConsoleInternApplicationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsoleInternFileService {
    private final ConsoleInternApplicationDAO resumeFileDAO;

//    조회
    public Optional<FileDTO> getDownloadUrl(Long memberId, Long internNoticeId) {
        return resumeFileDAO.findResumeFileByMemberId(memberId, internNoticeId);
    }

}
