package com.example.kok.service;

import com.example.kok.repository.FollowDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowDAO followDAO;

    @Override
    public void follow(Long memberId, Long companyId) {
        if (!followDAO.isFollowing(memberId, companyId)) {
            followDAO.insertFollow(memberId, companyId);
        }
    }

    @Override
    public void unfollow(Long memberId, Long companyId) {
        if (followDAO.isFollowing(memberId, companyId)) {
            followDAO.deleteFollow(memberId, companyId);
        }
    }

    @Override
    public boolean isFollowing(Long memberId, Long companyId) {
        return followDAO.isFollowing(memberId, companyId);
    }

    @Override
    public int countFollowers(Long companyId) {
        return followDAO.countFollowersByCompanyId(companyId);
    }

    @Override
    public int countFollowing(Long memberId) {
        return followDAO.selectFollowingCountByMemberId(memberId);
    }
}
