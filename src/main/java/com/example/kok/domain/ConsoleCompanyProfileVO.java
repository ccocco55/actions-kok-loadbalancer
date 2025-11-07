package com.example.kok.domain;

import com.example.kok.audit.Period;
import com.example.kok.enumeration.RequestStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="companyId", callSuper = false)
@SuperBuilder
public class ConsoleCompanyProfileVO extends Period{
    private Long companyId;
    private String companyName;
    private String companyInfo;
    private String companyUrl;
    private String ceoName;
    private String companySectorName;
    private String companyScaleName;
}
