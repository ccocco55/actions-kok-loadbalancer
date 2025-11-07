package com.example.kok.service;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.*;
import com.example.kok.enumeration.UserRole;
import com.example.kok.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainpageServiceImpl implements MainpageService {
    private final CompanyDAO companyDAO;
    private final S3Service s3Service;
    private final InternNoticeDAO internNoticeDAO;
    private final ExperienceNoticeDAO experienceNoticeDAO;
    private final FollowDAO followDAO;
    private final CompanyProfileFileDAO companyProfileFileDAO;
    private final UserProfileService userProfileService;
    private final RequestInternDAO requestInternDAO;
    private final RequestExperienceDAO requestExperienceDAO;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<CompanyDTO> findPopularCompanies() {
        List<CompanyDTO> companyDTOs = followDAO.selectPopularCompany();
        companyDTOs.forEach(companyDTO -> {

            if(companyProfileFileDAO.findFileByCompanyId(companyDTO.getUserId())==null) {

            }else{
                companyDTO.setCompanyProfileFile(s3Service.getPreSignedUrl(companyProfileFileDAO.findFileByCompanyId(companyDTO.getUserId()).getFilePath(), Duration.ofMinutes(10)));
            }
            companyDTO.setFollowerCount(followDAO.countFollowersByCompanyId(companyDTO.getUserId()));
        });
        return companyDTOs;
    }

    @Override
    public List<ExperienceNoticeDTO> findExperienceNotices(String keyword) {
        List<ExperienceNoticeDTO> experienceNoticeDTOS = experienceNoticeDAO.findAllByKeyword(keyword);
        experienceNoticeDTOS.forEach(experienceNoticeDTO -> {
            if(companyProfileFileDAO.findFileByCompanyId(experienceNoticeDTO.getCompanyId())==null){}
            else{
                experienceNoticeDTO.setFilePath(s3Service.getPreSignedUrl(companyProfileFileDAO.findFileByCompanyId(experienceNoticeDTO.getCompanyId()).getFilePath(), Duration.ofMinutes(10)));
            }
        });
        return experienceNoticeDTOS;
    }

    @Override
    public List<InternNoticeDTO> findInternNotices(String keyword) {
        List<InternNoticeDTO>  internNoticeDTOS = internNoticeDAO.findAllByKeyword(keyword);
        internNoticeDTOS.forEach(internNoticeDTO -> {
            if(companyProfileFileDAO.findFileByCompanyId(internNoticeDTO.getCompanyId())==null){
            }else{
                internNoticeDTO.setFilePath(s3Service.getPreSignedUrl(companyProfileFileDAO.findFileByCompanyId(internNoticeDTO.getCompanyId()).getFilePath(), Duration.ofMinutes(10)));
            }
        });
        return internNoticeDTOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomUserDetails findProfile(CustomUserDetails customUserDetails) {
        if(customUserDetails.getMemberProfileUrl()!=null){
            if(userProfileService.findProfileById(customUserDetails.getId()) != null){
                customUserDetails.setMemberProfileUrl(userProfileService.findProfileById(customUserDetails.getId()));
            }
        }else {
            if(customUserDetails.getUserRole()== UserRole.MEMBER) {
                if (userProfileService.findProfileById(customUserDetails.getId()) != null) {
                    customUserDetails.setMemberProfileUrl(userProfileService.findProfileById(customUserDetails.getId()));
                } else {
                    customUserDetails.setMemberProfileUrl("/images/member/profile.png");
                }
            }else if(customUserDetails.getUserRole()== UserRole.COMPANY) {
                if(companyProfileFileDAO.findCountByCompanyId(customUserDetails.getId()) > 0) {
                    customUserDetails.setMemberProfileUrl(s3Service.getPreSignedUrl(companyProfileFileDAO.findFileByCompanyId(customUserDetails.getId()).getFilePath(), Duration.ofMinutes(10)));
                }else{
                    customUserDetails.setMemberProfileUrl("/images/member/profile.png");
                }
                customUserDetails.setCompanyName(companyDAO.findCompanyById(customUserDetails.getId()).getCompanyName());
            }else{
                return customUserDetails;
            }

        }
        return customUserDetails;
    }

    @Override
    public List<RequestExperienceDTO> findRequestExperienceByCompanyId(Long companyId, Long experienceId) {
        List<RequestExperienceDTO> requestExperienceDTO = requestExperienceDAO.selectAllRequestByUserId(companyId, experienceId);
        requestExperienceDTO.forEach(experienceDTO -> {
            var profileFile = companyProfileFileDAO.findFileByCompanyId(experienceDTO.getUserId());

            if (profileFile != null && profileFile.getFilePath() != null) {
                experienceDTO.setCompanyProfileUrl(
                        s3Service.getPreSignedUrl(profileFile.getFilePath(), Duration.ofMinutes(10))
                );
            } else {
                experienceDTO.setCompanyProfileUrl("/images/member/profile.png");
            }
        });
        return requestExperienceDTO;
    }

    @Override
    public List<RequestInternDTO> findRequestInternByCompanyId(Long companyId, Long internId) {
        List<RequestInternDTO> requestInternDTO = requestInternDAO.selectAllInternByUserId(companyId, internId);
        requestInternDTO.forEach(internDTO -> {
            var profileFile = companyProfileFileDAO.findFileByCompanyId(internDTO.getUserId());

            if (profileFile != null && profileFile.getFilePath() != null) {
                internDTO.setCompanyProfileUrl(
                        s3Service.getPreSignedUrl(profileFile.getFilePath(), Duration.ofMinutes(10))
                );
            } else {
                internDTO.setCompanyProfileUrl("/images/member/profile.png");
            }
        });
        return requestInternDTO;
    }
}
