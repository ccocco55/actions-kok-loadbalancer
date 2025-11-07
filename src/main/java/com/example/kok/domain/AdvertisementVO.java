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
public class AdvertisementVO extends Period{
    private Long id;
    private String advertisementMainText;
    private String advertisementSubText;
    private RequestStatus advertisementStatus;
    private String startAdvertise;
    private String endAdvertise;
    private Long companyId;
}
