package com.example.kok.mapper;

import com.example.kok.dto.PostLikeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommunityLikeMapper {
//    게시글 좋아요
    public void insertPostLike(PostLikeDTO postLikeDTO);

//    게시글 좋아요 취소
    public void deletePostLike(@Param("postId") Long postId, @Param("memberId") Long memberId);

//    게시글 좋아요 갯수
    public int countByPostId(Long postId);

//    좋아요 여부 확인
    public boolean checkPostLiked(@Param("postId") Long postId, @Param("memberId") Long memberId);
}
