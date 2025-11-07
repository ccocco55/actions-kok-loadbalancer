package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class AdminPaymentExperienceDTO {
    private Long id;
    private RequestStatus requestExperienceStatus;
    private Long experienceNoticeId;
    private Long memberId;

    private String userName;
    private String userEmail;
    private Long paymentPrice;
    private String paymentPriceText;
    private String paymentPaidDatetime;
    private String relativeDate;
    private String experienceNoticeTitle;
    private String companyName;
}
