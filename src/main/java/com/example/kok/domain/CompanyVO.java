package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="userId", callSuper = false)
@SuperBuilder
public class CompanyVO extends Period{
    private Long userId;
    private String companyName;
    private String companyInfo;
    private String companyUrl;
}
