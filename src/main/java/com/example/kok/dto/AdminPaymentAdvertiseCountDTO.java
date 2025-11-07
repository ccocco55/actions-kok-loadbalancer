package com.example.kok.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class AdminPaymentAdvertiseCountDTO {
    private Long acceptCount;
    private Long acceptTotal;
    private String acceptTotalText;
}
