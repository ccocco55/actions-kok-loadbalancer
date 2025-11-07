package com.example.kok.repository;

import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.mapper.ExperienceNoticeMapper;
import com.example.kok.util.CompanyNoticeCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExperienceNoticeDAO {
    private final ExperienceNoticeMapper experienceNoticeMapper;

    //    목록 조회
    public List<ExperienceNoticeDTO> findAll(Criteria criteria, Search search) {
        return experienceNoticeMapper.selectAllExperienceNotice(criteria, search);
    }

    //    개수 조회
    public int findCountAll(){
        return experienceNoticeMapper.selectCountAll();
    }

    //    단일 조회
    public ExperienceNoticeDTO findById(Long id){
        return experienceNoticeMapper.selectById(id);
    }
    //    직군 조회
    public String findJobNameByID(Long id){
        return experienceNoticeMapper.selectJobNameByExpId(id);
    }

    //    최신 체험 공고 4개 조회
    public List<ExperienceNoticeDTO> findLatestFour() {
        return experienceNoticeMapper.selectLatestFour();
    }

    //    기업별 체험 공고 목록
    public List<ExperienceNoticeDTO> findAllByCompanyId(Long companyId, CompanyNoticeCriteria criteria, Search search) {
        return experienceNoticeMapper.selectExperienceNoticeByCompanyId(companyId, criteria, search);
    }

    //    기업별 체험 공고 갯수
    public int findCountByCompanyId(Long companyId, Search search) {
        return experienceNoticeMapper.selectExperienceNoticeCountByCompanyId(companyId, search);
    }

    //    기업별 체험 공고 조회
    public List<ExperienceNoticeDTO> selectListById(Long userId) {
        return experienceNoticeMapper.selectListById(userId);
    };

    //    기업별 체험 개수 전체
    public int selectListCountById(Long userId) {
        return experienceNoticeMapper.companyExperienceCount(userId);
    }

    //    체험 공고 리스트 조회
    public List<ExperienceNoticeDTO> findAllByKeyword(String keyword) {
        return experienceNoticeMapper.selectAllByKeyword(keyword);
    };
    public ExperienceNoticeDTO findCompanyNameById(Long id){
        return experienceNoticeMapper.selectCompanyNameById(id);
    }
}
