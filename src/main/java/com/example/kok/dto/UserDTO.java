package com.example.kok.dto;

import com.example.kok.enumeration.Provider;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import com.example.kok.enumeration.UserRole;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class UserDTO {
    private long id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String companyName;
    private String companyUrl;
    private UserRole userRole;
    private Provider memberProvider;
    private String snsEmail;
    private String memberProfileUrl;
    private Status userStatus;
    private String createdDateTime;
    private String updatedDateTime;
    private String jobName;

    private String memberInfo;

}
