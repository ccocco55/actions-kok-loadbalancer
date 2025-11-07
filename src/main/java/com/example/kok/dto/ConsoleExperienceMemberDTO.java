package com.example.kok.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="userId")
public class ConsoleExperienceMemberDTO {
    private long userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String experienceNoticeTitle;
    private long experienceNoticeId;
    private String requestDate;
}
