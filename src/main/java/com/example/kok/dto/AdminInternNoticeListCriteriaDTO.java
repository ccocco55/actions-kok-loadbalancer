package com.example.kok.dto;

import com.example.kok.util.AdminExperienceListCriteria;
import com.example.kok.util.Criteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminInternNoticeListCriteriaDTO {
    private List<AdminInternNoticeListDTO> internNoticeList;
    private AdminExperienceListCriteria internListCriteria;
}
