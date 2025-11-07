package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class MemberAlarmSettingDTO {
    private Long id;
    private Long memberId;
    private Status companyExperienceNoticeAlarm;
    private Status companyInternNoticeAlarm;
    private String createdDateTime;
    private String updatedDateTime;
}
