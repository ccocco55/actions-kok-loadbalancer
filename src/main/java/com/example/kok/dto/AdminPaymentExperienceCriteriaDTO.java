package com.example.kok.dto;

import com.example.kok.util.Criteria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Getter
@Setter
@ToString
public class AdminPaymentExperienceCriteriaDTO {
    private List<AdminPaymentExperienceDTO> paymentExperienceList;
    private Criteria criteria;

    private AdminPaymentExperienceCountDTO paymentExperienceCount;
}
