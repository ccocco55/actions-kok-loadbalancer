package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="memberId")
public class SaveExperienceNoticeDTO {
    private long memberId;
    private long experienceNoticeId;
    private String createdDateTime;
    private String updatedDateTime;
}
