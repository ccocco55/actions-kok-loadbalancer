package com.example.kok.dto;

import com.example.kok.util.CompanyNoticeCriteria;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyExperienceNoticeCriteriaDTO {
    private List<ExperienceNoticeDTO> experiences;
    private CompanyNoticeCriteria criteria;
}
