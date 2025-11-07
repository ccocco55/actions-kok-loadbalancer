package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class CommentDTO{
    private Long id;
    private String commentContent;
    private Status commentStatus;
    private Long memberId;
    private Long postId;
    private String createdDateTime;
    private String updatedDateTime;

    private String relativeDate;
    private int likesCount;
    private String userName;
    private String jobName;
    private String memberProfileUrl;
    private boolean liked;
    private int totalReplyCount;
    private List<ReplyDTO> replies;
    private boolean owner;
    private String snsProfile;
}
