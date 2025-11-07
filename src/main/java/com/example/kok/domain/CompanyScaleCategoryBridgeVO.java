package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="companyId", callSuper = false)
@SuperBuilder
public class CompanyScaleCategoryBridgeVO extends Period{
    private Long companyId;
    private Long companyScaleCategoryId;
}
