package com.example.kok.dto;

import com.example.kok.enumeration.Provider;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="userId")
public class MemberDTO {
    private Long userId;
    private Provider memberProvider;
    private String memberProfileUrl;
    private String memberInfo;
    private String createdDateTime;
    private String updatedDateTime;
    private String jobName;
    private int postsCount;
    private String snsEmail;
}
