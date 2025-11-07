package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@ToString
public class AdminExperienceDTO {
    private Long id;
    private String experienceNoticeTitle;
    private String experienceNoticeSubtitle;
    private String experienceNoticeIntroduceJob;
    private String experienceNoticeEtc;
    private String experienceStartDate;
    private String experienceEndDate;
    private Status experienceNoticeStatus;
    private Long companyId;
    private String createdDatetime;
    private String updatedDatetime;

    private String companyName;
    private String jobName;
    private String experienceNoticeStartDate;
    private String experienceNoticeEndDate;
    private String experienceMainTasks;
}
