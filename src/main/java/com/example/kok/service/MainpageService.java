package com.example.kok.service;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface MainpageService {
    public List<CompanyDTO> findPopularCompanies();
    public List<ExperienceNoticeDTO> findExperienceNotices( String keyword);
    public List<InternNoticeDTO> findInternNotices( String keyword);
    public CustomUserDetails findProfile(CustomUserDetails customUserDetails);
    public List<RequestExperienceDTO> findRequestExperienceByCompanyId(Long companyId,Long experienceId);
    public List<RequestInternDTO> findRequestInternByCompanyId(Long userId,Long internId);
}
