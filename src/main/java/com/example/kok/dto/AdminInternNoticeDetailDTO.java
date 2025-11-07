package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class AdminInternNoticeDetailDTO {
    private Long id;
    private Long companyId;
    private String internNoticeTitle;
    private String internNoticeSubTitle;
    private String internNoticeIntroduceJob;
    private String internMainTasks;
    private Status internNoticeStatus;
    private String internNoticeStartDate;
    private String internNoticeEndDate;
    private String internNoticeEtc;
    private String internStartDate;
    private String internEndDate;
    private String createdDateTime;
    private String updatedDateTime;

    private String companyName;
}
