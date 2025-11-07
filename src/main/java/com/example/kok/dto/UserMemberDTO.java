package com.example.kok.dto;

import com.example.kok.enumeration.Provider;
import com.example.kok.enumeration.Status;
import com.example.kok.enumeration.UserRole;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class UserMemberDTO {
    private Long id;
    private String userName;
    private String userEmail;
    private String userPhone;
    private UserRole userRole;
    private String snsEmail;
    private Status userStatus;
    private String memberProfileUrl;
    private String filePath;
    private String memberInfo;
    private String avgScore;
    private String jobName;
    private List<RequestExperienceDTO> requestExperiences;
    private int requestExperienceCount;
    private List<PostDTO> posts;
    private List<RequestInternDTO> requestInterns;
    private int requestInternCount;
    private int postsCount;
    private int followingCount;
}
