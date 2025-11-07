package com.example.kok.service;

public interface FollowService {
//    팔로우
    public void follow(Long memberId, Long companyId);

//    팔로우 취소
    public void unfollow(Long memberId, Long companyId);

//    팔로우 여부 확인
    public boolean isFollowing(Long memberId, Long companyId);

//    기업 팔로워 수
    public int countFollowers(Long companyId);

//    회원 팔로잉 수
    public int countFollowing(Long memberId);
}
