package com.example.kok.dto;

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
public class RequestInternDTO {
    private long id;
    private RequestStatus requestInternStatus;
    private long memberId;
    private long internNoticeId;
    private long memberAlarmSettingId;
    private long evaluationId;
    private long userId;
    private String companyProfileUrl;
    private String companyName;
    private String internNoticeTitle;
    private String createdDateTime;
    private String updatedDateTime;
    private String requestInternMemberName;
    private String requestInternMemberEmail;
    private String requestInternMemberPhone;
    private String requestInternMemberUrl;
    private long fileId;
}
