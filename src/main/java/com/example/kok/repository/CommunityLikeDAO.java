package com.example.kok.repository;

import com.example.kok.dto.PostLikeDTO;
import com.example.kok.mapper.CommunityLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommunityLikeDAO {
    private final CommunityLikeMapper communityLikeMapper;

//    게시글 좋아요
    public void savePostLike(PostLikeDTO postLikeDTO) {
        communityLikeMapper.insertPostLike(postLikeDTO);
    }

//    게시글 좋아요 취소
    public void deletePostLike(Long postId, Long memberId) {
        communityLikeMapper.deletePostLike(postId, memberId);
    }

//    게시글 좋아요 갯수
    public int getPostLikeCount(Long postId) {
        return communityLikeMapper.countByPostId(postId);
    }

//    좋아요 여부 확인
    public boolean isexistLike(Long postId, Long memberId) {
        return communityLikeMapper.checkPostLiked(postId, memberId);
    }
}
