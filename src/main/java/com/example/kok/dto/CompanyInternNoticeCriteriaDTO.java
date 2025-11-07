package com.example.kok.dto;

import com.example.kok.util.CompanyNoticeCriteria;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInternNoticeCriteriaDTO {
    private List<InternNoticeDTO> interns;
    private CompanyNoticeCriteria criteria;
}
