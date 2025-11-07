package com.example.kok.service;

import com.example.kok.dto.ReplyDTO;

import java.util.List;

public interface CommunityReplyService {

//    대댓글 작성
    public void write(ReplyDTO replyDTO);

//    대댓글 수정
    public void update(ReplyDTO replyDTO);

//    대댓글 삭제
    public void delete(Long id);

//    댓글 내 대댓글 목록
    public List<ReplyDTO> getReplies(Long commentId, Long memberId);;

//    대댓글 갯수
    public int getRepliesCount(Long commentId);
}
