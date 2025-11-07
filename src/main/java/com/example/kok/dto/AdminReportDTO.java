package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import com.example.kok.util.Criteria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
public class AdminReportDTO {
    private long id;
    private long postId;
    private long memberId;

    private String userName;
    private String userEmail;

    private String postContent;
    private Status postStatus;
    private String createdDateTime;
    private String relativeDate;
    private List<PostFileDTO> postFiles;
}
