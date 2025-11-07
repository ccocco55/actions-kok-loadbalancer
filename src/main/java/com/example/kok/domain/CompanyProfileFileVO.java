package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="fileId", callSuper = false)
@SuperBuilder
public class CompanyProfileFileVO extends Period{
    private Long fileId;
    private Long companyId;
}
