package com.example.kok.mapper;

import com.example.kok.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityReplyMapper {

//    대댓글 작성
    public void insertReply(ReplyDTO replyDTO);

//    대댓글 수정
    public void updateReply(ReplyDTO replyDTO);

//    대댓글 삭제
    public void deleteReply(Long id);

//    댓글 내 대댓글 목록
    public List<ReplyDTO> selectRepliesByCommentId(Long commentId);

//    대댓글 갯수
    public int countRepliesByCommentId(Long commentId);
}
