package com.example.kok.service;

import com.example.kok.dto.CommentDTO;
import java.util.List;

public interface CommunityCommentService {

//    댓글 작성
    public void write(CommentDTO commentDTO);

//    댓글 수정
    public void update(CommentDTO commentDTO);

//    댓글 삭제
    public void delete(Long id);

//    게시글 내 댓글 목록
    public List<CommentDTO> getComments(Long postId, Long memberId);

//    게시글 내 댓글 갯수
    public int commentsCountByPostId(Long postId);
}
