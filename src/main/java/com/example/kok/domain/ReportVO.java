package com.example.kok.domain;

import com.example.kok.audit.Period;
import com.example.kok.enumeration.RequestStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@SuperBuilder
public class ReportVO extends Period{
    private long id;
    private long postId;
    private long memberId;
}
