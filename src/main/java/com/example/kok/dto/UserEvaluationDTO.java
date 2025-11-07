package com.example.kok.dto;

import com.example.kok.enumeration.Provider;
import com.example.kok.enumeration.Status;
import com.example.kok.enumeration.UserRole;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class UserEvaluationDTO {
    private long id;
    private String userName;
    private String userEmail;
    private String userPhone;
    private UserRole userRole;
    private Provider memberProvider;
    private String snsEmail;
    private Status userStatus;

    private String evaluationContent;
    private double evaluationAvgScore;
}
