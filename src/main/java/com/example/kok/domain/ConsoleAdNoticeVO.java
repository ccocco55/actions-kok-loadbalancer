package com.example.kok.domain;

import com.example.kok.audit.Period;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@SuperBuilder
public class ConsoleAdNoticeVO extends Period{
    private long id;
    private String advertisementMainText;
    private String advertisementSubText;
    private Status advertisementStatus;
    private RequestStatus advertisementRequestStatus;
    private String advertiseStartDatetime;
    private String advertiseEndDatetime;
    private long companyId;
    private long paymentPrice;
}
