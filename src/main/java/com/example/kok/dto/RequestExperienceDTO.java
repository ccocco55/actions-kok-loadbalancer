package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class RequestExperienceDTO {
    private long id;
    private RequestStatus requestExperienceStatus;
    private long memberId;
    private long memberAlarmSettingId;
    private long experienceNoticeId;
    private String companyName;
    private long userId;
    private String companyProfileUrl;
    private Double evaluationAvgScore;
    private String experienceNoticeTitle;
    private String createdDateTime;
    private String updatedDateTime;
    private String requestExperienceMemberName;
    private String requestExperienceMemberEmail;
    private String requestExperienceMemberPhone;
    private String requestExperienceMemberUrl;
    private long fileId;
    private LocalDate experienceEndDate;
}
