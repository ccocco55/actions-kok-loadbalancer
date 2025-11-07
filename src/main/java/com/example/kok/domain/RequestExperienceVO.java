package com.example.kok.domain;

import com.example.kok.audit.Period;
import com.example.kok.enumeration.RequestStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@SuperBuilder
public class RequestExperienceVO extends Period{
    private long id;
    private RequestStatus requestExperienceStatus;
    private long memberId;
    private long memberAlarmSettingId;
    private long experienceNoticeId;
    private String requestExperienceMemberName;
    private String requestExperienceMemberEmail;
    private String requestExperienceMemberPhone;
    private String requestExperienceMemberUrl;
    private long fileId;

}
