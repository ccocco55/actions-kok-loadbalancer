package com.example.kok.domain;

import com.example.kok.audit.Period;
import com.example.kok.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@SuperBuilder
public class ExperienceNoticeVO extends Period{
    private Long id;
    private String experienceNoticeTitle;
    private String experienceNoticeSubtitle;
    private String experienceNoticeIntroduceJob;
    private String experienceNoticeEtc;
    private String experienceMainTasks;
    private LocalDate ExperienceStartDate;
    private LocalDate ExperienceEndDate;
    private Status experienceNoticeStatus;
    private Long companyId;
}
