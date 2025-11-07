package com.example.kok.mapper;

import com.example.kok.dto.CommentDTO;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityCommentMapper {

//    댓글 작성
    public void insertComment(CommentDTO commentDTO);

//    댓글 수정
    public void updateComment(CommentDTO commentDTO);

//    댓글 삭제
    public void deleteComment(Long id);

//    게시글 내 댓글 목록
    public List<CommentDTO> selectCommentsByPostId(Long postId);

//    댓글 갯수
    public int countCommentsByPostId(Long postId);
}
