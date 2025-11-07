package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.util.Search;

import java.util.List;

public interface ExperienceNoticeService {

//    목록 조회
    public ExperienceNoticeCriteriaDTO selectAllExperienceNotice(int page, Search search);

//    기업 프로필 사진 url
    public void setPreSignedUrl(CompanyProfileFileDTO companyProfileFileDTO);

//    공고 단일 조회
    public ExperienceNoticeDTO findNoticeById(Long id);

//    공고 저장
    public void saveExp(SaveExperienceNoticeDTO saveExperienceNoticeDTO);

//    공고 저장 취소
    public void deleteExp(SaveExperienceNoticeDTO saveExperienceNoticeDTO);

//    최신 체험 공고 4개 조회
    public List<ExperienceNoticeDTO> findLatestFour();

//    공고 저장 여부 판별
    public boolean isSavedExp(SaveExperienceNoticeDTO saveExperienceNoticeDTO);

//    공고 지원 여부 판별
    public boolean isRequested(RequestExperienceDTO requestExperienceDTO);

//    기업별 체험 공고
    public CompanyExperienceNoticeCriteriaDTO getExperienceNoticesByCompanyId(int page, Long companyId, Search search);

//    커뮤니티 쪽 체험 공고 4개 조회
    public List<ExperienceNoticeDTO> findLatestFourExperience();

//    가장 최근 배너 조회
    public String getBanner();
}
