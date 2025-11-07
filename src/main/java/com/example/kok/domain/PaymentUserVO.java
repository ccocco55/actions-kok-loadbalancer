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
public class PaymentUserVO extends Period{
    private long id;
    private String paidDatetime;
    private long paymentId;
    private long userId;
    private long requestExperienceId;
    private long advertisementId;
}
