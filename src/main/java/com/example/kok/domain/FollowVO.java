package com.example.kok.domain;

import com.example.kok.audit.Period;
import com.example.kok.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@SuperBuilder
public class FollowVO extends Period{
    private Long id;
    private Long memberId;
    private Long companyId;
    private Status followStatus;
}
