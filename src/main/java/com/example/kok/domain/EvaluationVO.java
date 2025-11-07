package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@SuperBuilder
public class EvaluationVO extends Period{
    private Long id;
    private String evaluationContent;
    private double evaluationAvgScore;
    private Long requestExperienceId;
    private Long memberId;
    private Long companyId;
}
