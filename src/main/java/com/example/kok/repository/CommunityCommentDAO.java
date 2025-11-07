package com.example.kok.repository;

import com.example.kok.dto.CommentDTO;
import com.example.kok.mapper.CommunityCommentMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommunityCommentDAO {
    private final CommunityCommentMapper communityCommentMapper;

//    댓글 작성
    public void save(CommentDTO commentDTO) {
        communityCommentMapper.insertComment(commentDTO);
    }

//    댓글 수정
    public void update(CommentDTO commentDTO) {
        communityCommentMapper.updateComment(commentDTO);
    }

//    댓글 삭제
    public void delete(Long id) {
        communityCommentMapper.deleteComment(id);
    }

//    게시글 내 댓글 목록
    public List<CommentDTO> findAll(Long postId) {
        return communityCommentMapper.selectCommentsByPostId(postId);
    }

//    댓글 갯수
    public int getTotal(Long postId) {
        return communityCommentMapper.countCommentsByPostId(postId);
    }
}
