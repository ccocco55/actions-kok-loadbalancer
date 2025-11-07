package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="companyId")
@NoArgsConstructor
public class CompanyScaleCategoryBridgeDTO{
    private Long companyId;
    private Long companyScaleCategoryId;
}
