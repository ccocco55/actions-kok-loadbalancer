package com.example.kok.dto;

import com.example.kok.util.Criteria;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter @ToString
public class ConsoleExperienceApplicantCriteriaDTO {
    // 공고 리스트
    private List<ConsoleExperienceApplicantDTO> applicantLists;

    private int totalCount;
    private Criteria criteria;
}
