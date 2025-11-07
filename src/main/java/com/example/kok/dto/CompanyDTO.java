package com.example.kok.dto;

import com.example.kok.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="userId")
@NoArgsConstructor
public class CompanyDTO{
    private Long userId;
    private String companyName;
    private String companyInfo;
    private String companyUrl;
    private String createdDatetime;
    private String updatedDatetime;

    private String scaleName;
    private int followerCount;
    private int experienceCount;
    private int internCount;

    private String companyBackgroundFile;
    private String companyProfileFile;
    private String companySectorName;

    private int experienceCountByEndDate;
    private int internCountByEndDate;
}
