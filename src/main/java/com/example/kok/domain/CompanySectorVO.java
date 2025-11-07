package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@SuperBuilder
public class CompanySectorVO extends Period{
    private Long id;
    private String companySectorName;
}
