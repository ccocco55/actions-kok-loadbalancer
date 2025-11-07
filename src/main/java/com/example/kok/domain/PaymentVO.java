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
public class PaymentVO extends Period{
    private long id;
    private long paymentPrice;
    private Status paymentStatus;
    private String paymentPaidDateTime;
    private long advertisementId;
    private long requestExperienceId;
    private long userId;
}
