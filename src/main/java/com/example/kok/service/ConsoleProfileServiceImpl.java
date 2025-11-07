package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.*;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsoleProfileServiceImpl implements ConsoleProfileService {
    private final ConsoleProfileDAO consoleProfileDAO;
    private final ConsoleProfileFileDAO consoleProfileFileDAO;
    private final ConsoleBackgroundFileDAO consoleBackgroundFileDAO;
    private final S3Service s3Service;

//    조회
    @Override
    @Cacheable(value = "profile", key = "'company_' + #companyId")
    public ConsoleCompanyProfileDTO getProfile(Long companyId) {
        // 기업 기본 정보 조회
        ConsoleCompanyProfileDTO profile = consoleProfileDAO.findCompanyProfileByUserId(companyId);

        // 프로필 이미지 조회
        List<FileDTO> profileFiles = consoleProfileFileDAO.findAllByProfileId(companyId);

        // 배경 이미지 조회
        List<FileDTO> backgroundFiles = consoleBackgroundFileDAO.findAllByProfileId(companyId);

        if (profileFiles != null) {
            for (FileDTO file : profileFiles) {
                if (file.getFilePath() != null && !file.getFilePath().startsWith("https")) {
                    String preSignedUrl = s3Service.getPreSignedUrl(file.getFilePath(), Duration.ofMinutes(5));
                    file.setFilePath(preSignedUrl);
                }
            }
        }

        if (backgroundFiles != null) {
            for (FileDTO file : backgroundFiles) {
                if (file.getFilePath() != null && !file.getFilePath().startsWith("https")) {
                    String preSignedUrl = s3Service.getPreSignedUrl(file.getFilePath(), Duration.ofMinutes(5));
                    file.setFilePath(preSignedUrl);
                }
            }
        }

        profile.setUploadedFiles(profileFiles);
        profile.setBackgroundFiles(backgroundFiles);

        return profile;
    }

    private String getPath() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return today.format(formatter);
    }

    public void setPreSignedUrl(ConsoleCompanyProfileDTO profileDTO) {
        List<FileDTO> files = consoleProfileFileDAO.findAllByProfileId(profileDTO.getCompanyId());

        files.forEach(file -> {
            file.setFilePath(
                s3Service.getPreSignedUrl(file.getFilePath(), Duration.ofMinutes(5))
            );
        });

        profileDTO.setUploadedFiles(files);
    }

//    수정
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "profile", key = "'company_' + #companyProfileDTO.companyId")
    public void updateProfile(ConsoleCompanyProfileDTO companyProfileDTO, List<MultipartFile> multipartFiles) {
//        기본 선택 값
        if (companyProfileDTO.getCompanyScaleName() == null || companyProfileDTO.getCompanyScaleName().isBlank()) {
            companyProfileDTO.setCompanyScaleName("미선택");
        }
        if (companyProfileDTO.getCompanySectorName() == null || companyProfileDTO.getCompanySectorName().isBlank()) {
            companyProfileDTO.setCompanySectorName("미선택");
        }

//        기본 기업 정보 수정
        consoleProfileDAO.updateCompanyProfile(toConsoleProfileVO(companyProfileDTO));
        consoleProfileDAO.updateCeoName(toConsoleProfileVO(companyProfileDTO));
        consoleProfileDAO.updateCompanySector(toConsoleProfileVO(companyProfileDTO));
        consoleProfileDAO.updateCompanyScale(toConsoleProfileVO(companyProfileDTO));

//        이미지 업로드 처리 (프로필 + 배경)
        if (multipartFiles != null && !multipartFiles.isEmpty()) {

            boolean hasProfileFile = false;
            boolean hasBackgroundFile = false;

            for (MultipartFile multipartFile : multipartFiles) {
                if (!multipartFile.isEmpty()) {
                    if (multipartFile.getName().contains("background")) {
                        hasBackgroundFile = true;
                    } else {
                        hasProfileFile = true;
                    }
                }
            }

            // 기존 파일 및 연결 삭제
            if (hasProfileFile) {
                consoleProfileFileDAO.deleteAllFilesByProfileId(companyProfileDTO.getCompanyId());
            }

            if (hasBackgroundFile) {
                consoleBackgroundFileDAO.deleteAllFilesByProfileId(companyProfileDTO.getCompanyId());
            }

            multipartFiles.forEach(multipartFile -> {
                if (multipartFile.isEmpty()) return;

                try {
                    // S3 업로드
                    String s3Key = s3Service.uploadFile(multipartFile, getPath());

                    // 파일 DTO 생성
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
                    fileDTO.setFileName(UUID.randomUUID().toString());
                    fileDTO.setFilePath(s3Key);
                    fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
                    fileDTO.setFileContentType(multipartFile.getContentType());

                    // tbl_file 등록
                    consoleProfileFileDAO.saveFile(fileDTO);

                    // 연결 DTO 생성
                    ConsoleProfileFileDTO consoleProfileFileDTO = new ConsoleProfileFileDTO();
                    consoleProfileFileDTO.setFileId(fileDTO.getId());
                    consoleProfileFileDTO.setCompanyId(companyProfileDTO.getCompanyId());

                    // 파일 이름이나 name 값으로 구분
                    String inputName = multipartFile.getName();
                    if (inputName.contains("background")) {
                        consoleBackgroundFileDAO.linkFileToProfile(consoleProfileFileDTO);
                    } else {
                        consoleProfileFileDAO.linkFileToProfile(consoleProfileFileDTO);
                    }

                } catch (IOException e) {
                    throw new RuntimeException("이미지 업로드 실패");
                }
            });
        }
    }
}
