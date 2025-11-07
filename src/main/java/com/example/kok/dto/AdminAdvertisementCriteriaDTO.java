package com.example.kok.dto;

import com.example.kok.util.AdminAdvertisementCriteria;
import com.example.kok.util.Criteria;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminAdvertisementCriteriaDTO {
    private List<AdminAdvertisementDTO> advertisements;
    private AdminAdvertisementCriteria criteria;

    private AdminAdvertisementCountDTO countDTO;
}
