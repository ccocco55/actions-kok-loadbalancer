package com.example.kok.dto;

import com.example.kok.enumeration.PostWarningStatus;
import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class PostWarningDTO {
    private long id;
    private String postContent;
    private Status postStatus;
    private PostWarningStatus postWarningStatus;
    private long memberId;
    private String userEmail;
    private String userName;
    private int wraningCount;

}
