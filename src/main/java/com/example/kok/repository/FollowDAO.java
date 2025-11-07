package com.example.kok.repository;

import com.example.kok.dto.CompanyDTO;
import com.example.kok.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class FollowDAO {
    private final FollowMapper followMapper;

    public List<CompanyDTO> selectPopularCompany() {
        return followMapper.selectPopularCompany();
    }
//    팔로우
    public void insertFollow(Long memberId, Long companyId) {
        followMapper.insertFollow(memberId, companyId);
    }

//    팔로우 취소
    public void deleteFollow(Long memberId, Long companyId) {
        followMapper.deleteFollow(memberId, companyId);
    }

//    팔로우 여부 확인
    public boolean isFollowing(Long memberId, Long companyId) {
        return followMapper.isFollowing(memberId, companyId);
    }

//    기업 팔로워 수
    public int countFollowersByCompanyId(Long companyId) {
        return followMapper.countFollowersByCompanyId(companyId);
    }

//    회원 팔로잉 수
    public int selectFollowingCountByMemberId(Long memberId) {
        return followMapper.selectFollowingCountByMemberId(memberId);
    }
//    팔로우 전체 수 조회
    public int selectAllFollow() {
        return followMapper.selectAllFollow();
    }
}
