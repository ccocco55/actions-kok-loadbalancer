package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import com.example.kok.enumeration.UserRole;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="memberId")
public class SaveInternNoticeDTO {
    private long memberId;
    private long internNoticeId;
    private String createdDateTime;
    private String updatedDateTime;
}
