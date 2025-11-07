package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.repository.*;
import com.example.kok.util.Criteria;
import com.example.kok.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyDAO companyDAO;
    private final S3Service s3Service;
    private final InternNoticeDAO internNoticeDAO;
    private final ExperienceNoticeDAO experienceNoticeDAO;
    private final FollowDAO followDAO;
    private final CompanyProfileFileDAO companyProfileFileDAO;


    @Override
    public CompanyDTO findCompanyById(Long companyId) {
        CompanyDTO company = companyDAO.findCompanyById(companyId);
        if (company == null) {
            return null;
        }

        if (company.getCompanyProfileFile() != null) {
            company.setCompanyProfileFile(
                    s3Service.getPreSignedUrl(company.getCompanyProfileFile(), Duration.ofMinutes(10))
            );
        }

        if (company.getCompanyBackgroundFile() != null) {
            company.setCompanyBackgroundFile(
                    s3Service.getPreSignedUrl(company.getCompanyBackgroundFile(), Duration.ofMinutes(10))
            );
        }

        int followerCount = companyDAO.findFollowCount(companyId);
        company.setFollowerCount(followerCount);

        int experienceCount = companyDAO.findExperienceById(companyId);
        company.setExperienceCount(experienceCount);

        int internCount = companyDAO.findInternById(companyId);
        company.setInternCount(internCount);

        int experienceCountByEndDate = companyDAO.findExperienceByEndDate(companyId);
        company.setExperienceCountByEndDate(experienceCountByEndDate);

        int internCountEndDate = companyDAO.findInternByEndDate(companyId);
        company.setInternCountByEndDate(internCountEndDate);

        String scaleName = companyDAO.findScaleById(companyId);
        company.setScaleName(scaleName);

        return company;
    }

    @Override
    public AdminCompanyCriteriaDTO findAllCompanies(int page, String keyword) {
        AdminCompanyCriteriaDTO adminCompanyCriteriaDTO = new AdminCompanyCriteriaDTO();
        Criteria criteria = new Criteria(page, companyDAO.selectCompanyCount(keyword));

        List<AdminCompanyDTO> companies = companyDAO.selectCompanyList(criteria, keyword);

        criteria.setHasMore(companies.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

        criteria.setHasMore(companies.size() == criteria.getRowCount() + 1);
//        10개 가져왔으면, 마지막 1개 삭제
        if (criteria.isHasMore()) {
            companies.remove(companies.size() - 1);
        }

        adminCompanyCriteriaDTO.setAdminCompanyDTOList(companies);
        adminCompanyCriteriaDTO.setCriteria(criteria);
        adminCompanyCriteriaDTO.setTotal(companyDAO.selectCompanyCount(keyword));

        return adminCompanyCriteriaDTO;
    }

    //    아이디로 기업 조회
    @Override
    @Cacheable(value = "company", key = "'company_' + #userId")
    public AdminCompanyDTO findCompany(Long userId) {
        AdminCompanyDTO adminCompanyDTO = companyDAO.selectCompany(userId).orElse(null);

        if (adminCompanyDTO == null) {
            return null;
        }

        List<InternNoticeDTO> internNotices = internNoticeDAO.findInternNotices(userId);
        List<ExperienceNoticeDTO> experienceNotices = experienceNoticeDAO.selectListById(userId);
        int internNoticeCount = internNoticeDAO.internNoticeCount(userId);
        int experienceNoticeCount = experienceNoticeDAO.selectListCountById(userId);

        log.info("experienceNoticeCount {}:", experienceNoticeCount);

        adminCompanyDTO.setInternNoticeDTO(internNotices);
        adminCompanyDTO.setExperienceNoticeDTO(experienceNotices);
        adminCompanyDTO.setInternNoticeCount(internNoticeCount);
        adminCompanyDTO.setExperienceNoticeCount(experienceNoticeCount);

        int followCount = companyDAO.findFollowCount(userId);
        adminCompanyDTO.setFollowCount(followCount);


        return adminCompanyDTO;

    }


    //    기업 목록
    @Override
    @Transactional(rollbackFor = Exception.class)
//    실행되는 동안 오류 발생 시 롤백하기 위한 선언
    public CompaniesCriteriaDTO getCompanyList(int page, CompanySearch search, Long userId) {
//        System.out.println("검색 한 내용 " + search);

        Criteria criteria = new Criteria(page, companyDAO.findTotalCountByUserId(search));
        List<CompanyDTO> companies = companyDAO.findCompanies(criteria, search, userId);
//        System.out.println("기업들" + companies);

        criteria.setHasMore(criteria.getPage() < criteria.getRealEnd());
        if (criteria.isHasMore() && !companies.isEmpty()) {
            companies.remove(companies.size() - 1);
        }

        companies.forEach(company -> {
            if (company.getCompanyProfileFile() != null) {
                company.setCompanyProfileFile(
                        s3Service.getPreSignedUrl(company.getCompanyProfileFile(), Duration.ofMinutes(10))
                );
            }

            if (company.getCompanyBackgroundFile() != null) {
                company.setCompanyBackgroundFile(
                        s3Service.getPreSignedUrl(company.getCompanyBackgroundFile(), Duration.ofMinutes(10))
                );
            }
        });

        CompaniesCriteriaDTO companiesCriteriaDTO = new CompaniesCriteriaDTO();
        companiesCriteriaDTO.setCompanies(companies);
        companiesCriteriaDTO.setCriteria(criteria);

        return companiesCriteriaDTO;
    }

    //    인기 기업 목록
    @Override
    public List<CompanyDTO> getCompaniesByFollowerCount() {
        List<CompanyDTO> companies = companyDAO.findCompaniesByFollowerCount();

        companies.forEach(company -> {
            if (company.getCompanyProfileFile() != null) {
                company.setCompanyProfileFile(
                        s3Service.getPreSignedUrl(company.getCompanyProfileFile(), Duration.ofMinutes(10))
                );
            }

            if (company.getCompanyBackgroundFile() != null) {
                company.setCompanyBackgroundFile(
                        s3Service.getPreSignedUrl(company.getCompanyBackgroundFile(), Duration.ofMinutes(10))
                );
            }
        });

        return companies;
    }
}
