package com.example.kok.mapper;


import com.example.kok.dto.CompanyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowMapper{
    public List<CompanyDTO> selectPopularCompany();


//    팔로우
    public void insertFollow(@Param("memberId") Long memberId, @Param("companyId") Long companyId);

//    팔로우 취소
    public void deleteFollow(@Param("memberId") Long memberId, @Param("companyId") Long companyId);

//    팔로우 여부 확인
    public boolean isFollowing(@Param("memberId") Long memberId, @Param("companyId") Long companyId);

//    기업 팔로워 수
    public int countFollowersByCompanyId(@Param("companyId") Long companyId);

//    회원 팔로잉 수
    public int selectFollowingCountByMemberId (Long memberId);
//    팔로우 전체 수 확인
    public int selectAllFollow();

}
