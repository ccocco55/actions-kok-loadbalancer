package com.example.kok.repository;

import com.example.kok.dto.ReplyDTO;
import com.example.kok.mapper.CommunityReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommunityReplyDAO {
    private final CommunityReplyMapper communityReplyMapper;

//    대댓글 작성
    public void save(ReplyDTO replyDTO) {
        communityReplyMapper.insertReply(replyDTO);
    }

//    대댓글 수정
    public void update(ReplyDTO replyDTO) {
        communityReplyMapper.updateReply(replyDTO);
    }

//    대댓글 삭제
    public void delete(Long id) {
        communityReplyMapper.deleteReply(id);
    }

//    댓글 내 대댓글 목록
    public List<ReplyDTO> findAll(Long commentId) {
        return communityReplyMapper.selectRepliesByCommentId(commentId);
    }

//    대댓글 갯수
    public int countByCommentId(Long commentId) {
        return communityReplyMapper.countRepliesByCommentId(commentId);
    }
}
