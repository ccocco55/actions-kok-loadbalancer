package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class ConsoleInternNoticeDTO {
    private long id;
    private String internNoticeTitle;
    private String internNoticeSubtitle;
    private LocalDate internNoticeStartDate;
    private LocalDate internNoticeEndDate;
    private Status internNoticeStatus;
    private long companyId;
    private String createdDateTime;
    private String updatedDateTime;

    private String jobCategoryName;
    private int applicantCount; //지원자수
    private int saveCount; //저장한 사람수
}
