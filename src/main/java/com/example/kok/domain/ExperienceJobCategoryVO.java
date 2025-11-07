package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="experienceNoticeId")
@SuperBuilder
public class ExperienceJobCategoryVO extends Period{
    private long experienceNoticeId;
    private long jobCategoryId;
}
