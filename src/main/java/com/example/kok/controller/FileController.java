package com.example.kok.controller;

import com.example.kok.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/**")
public class FileController {
    private final FileDAO fileDAO;

    @GetMapping("/company-profile")
    public ResponseEntity<Resource> companyProfile(
            @RequestParam String filePath,
            @RequestParam String fileName
    ) throws IOException {
        String fullPath="C:/kokFile/"+filePath+"/"+fileName;
        File file=new File(fullPath);
        if(!file.exists()||!file.isFile()){
            File deleteFile=new File("src/main/resources/static/images/main-page/image.png");

            if(!deleteFile.exists()){
                return  ResponseEntity.notFound().build();
            }

            Resource defaultResource=new FileSystemResource(deleteFile);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE)
                    .body(defaultResource);
        }

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(file.toPath()))
            .body(resource);
    }
}
