package com.example.kok.controller;

import com.example.kok.service.ConsoleInternFileService;
import com.example.kok.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/files/intern")
public class ConsoleInternFileDownloadController {
    private final ConsoleInternFileService fileService;
    private final S3Service s3Service;

    @GetMapping("/download/{internNoticeId}/{memberId}")
    public ResponseEntity<String> downloadFile(@PathVariable Long internNoticeId, @PathVariable Long memberId) {

        var file = fileService.getDownloadUrl(memberId, internNoticeId)
                .orElseThrow(() -> new RuntimeException("파일 정보를 찾을 수 없습니다."));

        String downloadUrl = s3Service.getPreSignedDownloadUrl(
                file.getFilePath(),
                file.getFileOriginName(),
                Duration.ofMinutes(5)
        );

        return ResponseEntity.ok(downloadUrl);
    }
}
