package com.example.kok.mapper;

import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.util.CompanyNoticeCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.dto.InternNoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InternNoticeMapper {
    //    기업별 인턴 공고 목록
    public List<InternNoticeDTO> selectInternNoticeByCompanyId(@Param("companyId") Long companyId, @Param("criteria") CompanyNoticeCriteria criteria, @Param("search") Search search);
    //    전체 목록 조회
    public List<InternNoticeDTO> selectAllInternNotice(@Param("criteria")Criteria criteria, @Param("search")Search search);

    //    전체 개수 조회
    public int selectCountAll();

    //    기업별 인턴 공고 개수
    public int selectInternNoticeCountByCompanyId(@Param("companyId") Long companyId, @Param("search") Search search);
    //    단일 조회
    public InternNoticeDTO selectById(Long id);

    //    기업별 인턴 공고 목록
    public List<InternNoticeDTO> selectListById(Long userId);

    //    기업별 인턴 공고 개수
    public int selectListCountById(Long userId);

    //    직군 조회
    public String selectJobNameByIntId(Long id);

    //    최신 체험 공고 4개 조회
    public List<InternNoticeDTO> selectLatestFour();
    //    인턴 공고 리스트 조회
    public List<InternNoticeDTO> selectAllByKeyword(String keyword);
    public InternNoticeDTO selectCompanyNameById(Long id);
}
