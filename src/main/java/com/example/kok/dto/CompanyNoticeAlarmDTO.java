package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class CompanyNoticeAlarmDTO{
    private Long id;
    private Long memberAlarmSettingId;
    private Long followId;
    private Long experienceNoticeId;
    private Long internNoticeId;
    private Long memberId;
    private String createdDateTime;
    private String updatedDateTime;
}
