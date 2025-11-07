package com.example.kok.dto;

import com.example.kok.audit.Period;
import com.example.kok.enumeration.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ExperienceNoticeDTO{
    private Long id;
    private String experienceNoticeTitle;
    private String experienceNoticeSubtitle;
    private String experienceNoticeIntroduceJob;
    private String experienceNoticeEtc;
    private String experienceMainTasks;
    private String experienceStartDate;
    private String experienceEndDate;
    private String experienceNoticeStartDate;
    private String experienceNoticeEndDate;
    private Status experienceNoticeStatus;
    private Long companyId;
    private String createdDatetime;
    private String updatedDatetime;

    private CompanyProfileFileDTO profileFile;
    private String filePath;
    private String fileName;
    private String companyName;
    private int scaleId;
    private int saveCount;
    private String jobName;
    private String companyScaleName;
    private Long remainingDays;
    private List<UserDTO> users;
}
