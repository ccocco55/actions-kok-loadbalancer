package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class AdminInternNoticeDetailRequestDTO {
    private Long id;
    private RequestStatus requestInternStatus;
    private String userName;
    private String userEmail;
    private String userPhone;

}
