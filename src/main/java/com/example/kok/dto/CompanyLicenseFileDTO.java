package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="fileId")
@NoArgsConstructor
public class CompanyLicenseFileDTO {
    private Long fileId;
    private Long companyId;

}
