package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class InternNoticeDTO {
    private Long id;
    private String internNoticeTitle;
    private String internNoticeSubtitle;
    private String internNoticeIntroduceJob;
    private String internNoticeMajorWork;
    private String internNoticeEtc;
    private String internMainTasks;
    private String internStartDate;
    private String internEndDate;
    private Status internNoticeStatus;
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
    private String internNoticeStartDate;
    private String internNoticeEndDate;
    private String createdDateTime;
    private String updatedDateTime;
}