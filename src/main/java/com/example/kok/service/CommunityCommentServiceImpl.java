package com.example.kok.service;

import com.example.kok.dto.CommentDTO;
import com.example.kok.dto.CommentsCriteriaDTO;
import com.example.kok.dto.ReplyDTO;
import com.example.kok.repository.CommunityCommentDAO;
import com.example.kok.repository.CommunityReplyDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityCommentServiceImpl implements CommunityCommentService {
    private final CommunityCommentDAO communityCommentDAO;
    private final CommunityReplyDAO communityReplyDAO;
    private final S3Service s3Service;

    //    댓글 작성
    @Override
    public void write(CommentDTO commentDTO) {
        communityCommentDAO.save(commentDTO);

    }

    //    댓글 수정
    @Override
    public void update(CommentDTO commentDTO) {
        communityCommentDAO.update(commentDTO);
    }

    //    댓글 삭제
    @Override
    public void delete(Long id) {
        communityCommentDAO.delete(id);
    }

    //    게시글 내 댓글 목록
    @Override
    @Transactional(rollbackFor = Exception.class)
//    실행되는 동안 오류 발생 시 롤백하기 위한 선언
    public List<CommentDTO> getComments(Long postId, Long memberId) {
        List<CommentDTO> comments = communityCommentDAO.findAll(postId);

        comments.forEach(comment -> {
            comment.setRelativeDate(DateUtils.toRelativeTime(comment.getCreatedDateTime()));
            comment.setOwner(memberId.equals(comment.getMemberId()));

            String fileProfile = comment.getMemberProfileUrl();
            String snsProfile = comment.getSnsProfile();

            if (fileProfile != null && !fileProfile.isEmpty()) {
                try {
                    comment.setMemberProfileUrl(
                            s3Service.getPreSignedUrl(fileProfile, Duration.ofMinutes(10))
                    );
                } catch (Exception e) {
                    comment.setMemberProfileUrl(fileProfile);
                }
            } else if (snsProfile != null && !snsProfile.isEmpty()) {
                comment.setMemberProfileUrl(snsProfile);
            } else {
                comment.setMemberProfileUrl("/images/member/profile.png");
            }

            List<ReplyDTO> replies = communityReplyDAO.findAll(comment.getId());
            replies.forEach(reply -> {
                reply.setRelativeDate(DateUtils.toRelativeTime(reply.getCreatedDateTime()));
                reply.setOwner(memberId.equals(reply.getMemberId()));

                String replyFileProfile = reply.getMemberProfileUrl();
                String replySnsProfile = reply.getSnsProfile();

                if (replyFileProfile != null && !replyFileProfile.isEmpty()) {
                    try {
                        reply.setMemberProfileUrl(
                                s3Service.getPreSignedUrl(replyFileProfile, Duration.ofMinutes(10))
                        );
                    } catch (Exception e) {
                        reply.setMemberProfileUrl(replyFileProfile);
                    }
                } else if (replySnsProfile != null && !replySnsProfile.isEmpty()) {
                    reply.setMemberProfileUrl(replySnsProfile);
                } else {
                    reply.setMemberProfileUrl("/images/member/profile.png");
                }
            });

            comment.setReplies(replies);
            comment.setTotalReplyCount(replies.size());
        });

        return comments;
    }

    @Override
    public int commentsCountByPostId(Long postId) {
        int commentCount = communityCommentDAO.getTotal(postId);
        List<CommentDTO> comments = communityCommentDAO.findAll(postId);

        int replyCount = 0;
        for(CommentDTO commentDTO : comments) {
            replyCount += communityReplyDAO.countByCommentId(commentDTO.getId());
        }
        return commentCount + replyCount;
    }
}
