package com.example.kok.service;

import com.example.kok.dto.PostLikeDTO;

public interface CommunityLikeService {
//    게시글 좋아요
    public void postLike(PostLikeDTO postLikeDTO);

//    게시글 좋아요 취소
    public void removePostLike(Long postId, Long memberId);

//    게시글 좋아요 갯수
    public int getPostLikeCount(Long postId);

//    좋아요 여부 확인
    public boolean checkedPostLike(Long postId, Long memberId);
}
