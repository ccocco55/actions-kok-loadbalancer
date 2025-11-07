package com.example.kok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
public class ChartDTO {
    private String year;
    private String month;
    private int monthCount;
    private int experienceMonthCount;
    private int internRequestMonthCount;
    private int internNoticeMonthCount;
    private Long experiencePayment;
    private Long advertisementPayment;
}
