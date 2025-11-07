package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class AdminPaymentAdvertiseDTO {
    private Long id;
    private Long paymentPrice;
    private String paymentPriceText;
    private RequestStatus paymentStatus;
    private String paymentPaidDatetime;
    private String relativeDate;
    private Long advertisementId;
    private Long requestExperienceId;

    private String companyName;
    private String userEmail;
    private String advertiseStartDatetime;
    private String advertiseEndDatetime;
    private RequestStatus advertisementStatus;
}
