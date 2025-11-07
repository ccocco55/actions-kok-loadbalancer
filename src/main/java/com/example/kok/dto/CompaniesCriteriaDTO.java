package com.example.kok.dto;

import com.example.kok.util.Criteria;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompaniesCriteriaDTO {
    private List<CompanyDTO> companies;
    private Criteria criteria;
}
