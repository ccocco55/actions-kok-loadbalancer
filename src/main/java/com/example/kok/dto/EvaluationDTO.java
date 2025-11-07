package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class EvaluationDTO {
    private Long id;
    private String evaluationContent;
    private double evaluationAvgScore;
    private Long requestExperienceId;
    private Long memberId;
    private Long companyId;
    private String createdDateTime;
    private String updatedDateTime;
}
