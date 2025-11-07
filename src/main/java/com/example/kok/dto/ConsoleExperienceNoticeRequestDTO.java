package com.example.kok.dto;

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
public class ConsoleExperienceNoticeRequestDTO {
    private long id;
    private String experienceNoticeTitle; // 공고 제목
    private String experienceNoticeSubtitle; // 공고 부제목

    private LocalDate experienceNoticeStartDate; // 게시 시작일
    private LocalDate experienceNoticeEndDate; // 게시 종료일
    private LocalDate experienceStartDate; // 체험 시작일
    private LocalDate experienceEndDate; // 체험 종료일

    private long companyId; // 어떤 회사 공고인지

    private String experienceNoticeIntroduceJob; // 직무 소개
    private String experienceMainTasks; // 주요 업무
    private String experienceNoticeEtc; // 참고 사항

    private long jobCategoryId; // 직군 아이디
    private String jobCategoryName; // 직군 이름
}
