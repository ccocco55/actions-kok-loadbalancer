package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class PaymentUserDTO {
    private long id;
    private String paidDatetime;
    private long paymentId;
    private long userId;
    private long requestExperienceId;
    private long advertisementId;
}
