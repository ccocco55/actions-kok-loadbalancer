package com.example.kok.auth;

import com.example.kok.dto.MemberDTO;
import com.example.kok.dto.UserDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.enumeration.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@ToString
@Slf4j
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String createdDateTime;
    private String snsEmail;
    private String memberProfileUrl;
    private UserRole userRole;
    private Status userStatus;
    private String userJobName;
    private String companyName;
    private String memberInfo;

    public CustomUserDetails(UserDTO userDTO){
        log.info("CustomUserDetails:{}",userDTO.toString());
        this.id=userDTO.getId();
        this.userName=userDTO.getUserName();
        this.userPassword=userDTO.getUserPassword();
        this.userEmail=userDTO.getUserEmail();
        this.userPhone=userDTO.getUserPhone();
        this.createdDateTime=userDTO.getCreatedDateTime();
        this.snsEmail=userDTO.getSnsEmail();
        this.memberProfileUrl=userDTO.getMemberProfileUrl();
        this.userRole=userDTO.getUserRole();
        this.userStatus=userDTO.getUserStatus();
        this.userJobName=userDTO.getJobName();
        this.companyName = userDTO.getCompanyName();
        this.memberInfo=userDTO.getMemberInfo();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }
}
