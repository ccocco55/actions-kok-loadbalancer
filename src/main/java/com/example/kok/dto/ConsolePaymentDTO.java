package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class ConsolePaymentDTO {
    private long id;
    private long paymentPrice; // 결제 금액
    private Status paymentStatus; // 결제 상태
    private LocalDate paymentPaidDatetime; // 결제일

    private long userId; // 결제한 회사

    private String advertisementMainText; // 광고 제목

    private String createdDateTime;
    private String updatedDateTime;
}
