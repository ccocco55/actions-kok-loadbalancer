package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class PostDTO {
    private long id;
    private String postContent;
    private Status postStatus;
    private long memberId;
    private String createdDateTime;
    private String updatedDateTime;
    private String relativeDate;
    private List<PostFileDTO> postFiles;
    private int likesCount;
    private int commentsCount;
    private String userName;
    private String jobName;
    private String memberProfileUrl;
    private boolean liked;
    private List<CommentDTO> comments;
    private boolean owner;
    private String snsProfile;
}
