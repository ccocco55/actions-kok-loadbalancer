package com.example.kok.dto;

import com.example.kok.util.CompanyNoticeCriteria;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyAllNoticeCriteriaDTO {
    private List<ExperienceNoticeDTO> experiences;
    private List<InternNoticeDTO> interns;
    private CompanyNoticeCriteria criteria;
}
