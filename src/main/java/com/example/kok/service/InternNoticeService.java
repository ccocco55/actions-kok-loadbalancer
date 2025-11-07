package com.example.kok.service;

import com.example.kok.dto.CompanyInternNoticeCriteriaDTO;
import com.example.kok.dto.CompanyProfileFileDTO;
import com.example.kok.dto.InternNoticeCriteriaDTO;
import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.dto.SaveInternNoticeDTO;
import com.example.kok.util.Search;

import java.util.List;

public interface InternNoticeService {

//    기업별 인턴 공고 목록
    public CompanyInternNoticeCriteriaDTO getInternNoticesByCompanyId(int page, Long companyId, Search search);

//    목록 조회
    public InternNoticeCriteriaDTO selectAllInternNotice(int page, Search search);

//    기업 프로필 사진 url
    public void setPreSignedUrl(CompanyProfileFileDTO companyProfileFileDTO);

//    공고 단일 조회
    public InternNoticeDTO findNoticeById(Long id);

//    공고 저장
    public void saveInt(SaveInternNoticeDTO saveInternNoticeDTO);

//    공고 저장 취소
    public void deleteInt(SaveInternNoticeDTO saveInternNoticeDTO);

//    최신 체험 공고 4개 조회
    public List<InternNoticeDTO> findLatestFour();

//    공고 저장 여부 판별
    public boolean isSavedInt(SaveInternNoticeDTO saveInternNoticeDTO);
}
