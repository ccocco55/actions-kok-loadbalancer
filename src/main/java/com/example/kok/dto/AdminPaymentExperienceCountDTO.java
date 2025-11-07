package com.example.kok.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class AdminPaymentExperienceCountDTO {
    private Long acceptTotal;
    private String acceptTotalText;
    private Long rejectTotal;
    private String rejectTotalText;
}
