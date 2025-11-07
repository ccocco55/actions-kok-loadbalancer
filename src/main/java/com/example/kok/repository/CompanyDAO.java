package com.example.kok.repository;

import com.example.kok.domain.CompanyVO;
import com.example.kok.dto.AdminCompanyDTO;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.dto.UserMemberDTO;
import com.example.kok.mapper.CompanyMapper;
import com.example.kok.util.CompanySearch;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CompanyDAO {
    private final CompanyMapper companyMapper;

//    회사 조회
    public CompanyDTO findCompanyById(Long companyId) {
        return companyMapper.selectCompanyById(companyId);
    }

//    팔로워 수 조회
    public int findFollowCount(Long companyId){
        return companyMapper.selectFollowerCountByCompanyId(companyId);
    }

//    체험 공고 수 조회
    public int findExperienceById(Long companyId){
        return companyMapper.selectExperienceById(companyId);
    }

    public int findExperienceByEndDate(Long companyId){
        return companyMapper.selectExperienceByEndDate(companyId);
    }

//    인턴 공고 수 조회
    public int findInternById(Long companyId){
        return companyMapper.selectInternById(companyId);
    }

    public int findInternByEndDate(Long companyId){
        return companyMapper.selectInternByEndDate(companyId);
    }

//    회사 규모 조회
    public String findScaleById(Long companyId){
        return companyMapper.selectScaleById(companyId);
    }
    //    기업회원 회원 가입
    public void saveCompany(CompanyDTO  companyDTO){
        companyMapper.insertCompany(companyDTO);
    };

//    기업 목록
    public List<CompanyDTO> findCompanies(Criteria criteria, CompanySearch search, Long userId) {
        return companyMapper.selectCompanyAll(criteria, search, userId);
    }

//    기업 수
    public int findTotalCountByUserId(CompanySearch search) {
        return companyMapper.selectCompanyCountByUserId(search);
    }

//    인기 기업 목록
    public List<CompanyDTO> findCompaniesByFollowerCount() {
        return companyMapper.selectCompaniesByFollowerCount();
    }

    //    기업 목록 조회
    public List<AdminCompanyDTO> selectCompanyList(Criteria criteria, String keyword) {
        return companyMapper.selectCompanyList(criteria, keyword);
    };

    //    목록 개수 조회
    public int selectCompanyCount(String keyword) {
        return companyMapper.selectCompanyCount(keyword);
    };

    //    아이디로 회원 조회
    public Optional<AdminCompanyDTO> selectCompany(Long userId) {
        return companyMapper.selectCompany(userId);
    };
}
