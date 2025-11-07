package com.example.kok.repository;

import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.mapper.InternNoticeMapper;
import com.example.kok.util.CompanyNoticeCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InternNoticeDAO {
    private final InternNoticeMapper internNoticeMapper;

    //    기업별 인턴 공고 목록
    public List<InternNoticeDTO> findAllByCompanyId(Long companyId, CompanyNoticeCriteria criteria, Search search) {
        return internNoticeMapper.selectInternNoticeByCompanyId(companyId, criteria, search);
    }

    //    목록 조회
    public List<InternNoticeDTO> findAll(Criteria criteria, Search search) {
        return internNoticeMapper.selectAllInternNotice(criteria, search);
    }

    //    기업별 인턴 공고 갯수
    public int findCountByCompanyId(Long companyId, Search search) {
        return internNoticeMapper.selectInternNoticeCountByCompanyId(companyId, search);
    }

    //    개수 조회
    public int findCountAll(){
        return internNoticeMapper.selectCountAll();
    }

    //    기업회원 별 인턴 공고
    public List<InternNoticeDTO> findInternNotices(Long userId) {
        return internNoticeMapper.selectListById(userId);
    }

    // 기업별 인턴 개수
    public int internNoticeCount(Long userId) {
        return internNoticeMapper.selectListCountById(userId);
    }

    //    단일 조회
    public InternNoticeDTO findById(Long id){
        return internNoticeMapper.selectById(id);
    }
    //    직군 조회
    public String findJobNameByID(Long id){
        return internNoticeMapper.selectJobNameByIntId(id);
    }

    //    최신 체험 공고 4개 조회
    public List<InternNoticeDTO> findLatestFour() {
        return internNoticeMapper.selectLatestFour();
    }
    //    인턴 공고 리스트 조회
    public List<InternNoticeDTO> findAllByKeyword(String keyword) {
        return internNoticeMapper.selectAllByKeyword(keyword);
    };
    public InternNoticeDTO findCompanyNameById(Long id){
        return internNoticeMapper.selectCompanyNameById(id);
    }
}


