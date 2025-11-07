package com.example.kok.service;

import com.example.kok.domain.ConsoleCompanyProfileVO;
import com.example.kok.dto.ConsoleCompanyProfileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ConsoleProfileService {
//    조회
    ConsoleCompanyProfileDTO getProfile(Long userId);

//    수정
    void updateProfile(ConsoleCompanyProfileDTO companyProfileDTO, List<MultipartFile> multipartFiles);

    default ConsoleCompanyProfileVO toConsoleProfileVO(ConsoleCompanyProfileDTO dto) {
        return ConsoleCompanyProfileVO.builder()
                .companyId(dto.getCompanyId())
                .companyName(dto.getCompanyName())
                .companyInfo(dto.getCompanyInfo())
                .companyUrl(dto.getCompanyUrl())
                .ceoName(dto.getCeoName())
                .companySectorName(dto.getCompanySectorName())
                .companyScaleName(dto.getCompanyScaleName())
                .build();
    }

}
