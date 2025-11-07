package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminMainPageDTO {
    private double memberExperienceRequestAvg;
    private double memberInternRequestAvg;
    private double companyExperienceNoticeAvg;
    private double companyInternNoticeAvg;
    private int memberExperienceRequestTotal;
    private int memberInternRequestTotal;
    private int memberRequestTotal;
    private int companyExperienceNoticeTotal;
    private int companyInternNoticeTotal;
    private int companyNoticeTotal;
    private Long memberPaymentTotal;
    private String memberPaymentTotalText;
    private Long companyPaymentTotal;
    private String companyPaymentTotalText;
}
