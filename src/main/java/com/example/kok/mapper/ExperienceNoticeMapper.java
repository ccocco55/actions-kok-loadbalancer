package com.example.kok.mapper;

import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.util.CompanyNoticeCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExperienceNoticeMapper {
    //    전체 목록 조회
    public List<ExperienceNoticeDTO> selectAllExperienceNotice(@Param("criteria")Criteria criteria, @Param("search")Search search);

    //    전체 개수 조회
    public int selectCountAll();

    //    단일 조회
    public ExperienceNoticeDTO selectById(Long id);

    //    직군 조회
    public String selectJobNameByExpId(Long id);

    //    최신 체험 공고 4개 조회
    public List<ExperienceNoticeDTO> selectLatestFour();

    //    기업별 체험 공고 목록
    public List<ExperienceNoticeDTO> selectExperienceNoticeByCompanyId(@Param("companyId") Long companyId, @Param("criteria") CompanyNoticeCriteria criteria, @Param("search") Search search);

    //    기업별 체험 공고 개수
    public int selectExperienceNoticeCountByCompanyId(@Param("companyId") Long companyId, @Param("search") Search search);

    //    기업별 체험 공고 조회
    public List<ExperienceNoticeDTO> selectListById(Long userId);

    //    기업별 체험 공고 개수
    public int companyExperienceCount(Long userId);

    //    체험 공고 리스트 조회
    public List<ExperienceNoticeDTO> selectAllByKeyword(String keyword);
    public ExperienceNoticeDTO selectCompanyNameById(Long id);

    public String getTime();
}
