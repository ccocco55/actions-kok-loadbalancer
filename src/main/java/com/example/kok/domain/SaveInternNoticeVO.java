package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="memberId", callSuper = false)
@SuperBuilder
public class SaveInternNoticeVO extends Period{
    private long memberId;
    private long internNoticeId;

}
