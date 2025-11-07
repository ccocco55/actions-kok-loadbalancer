package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class MemberReplyAlarmDTO {
    private Long id;
    private Long memberId;
    private Long commentId;
    private Long replyId;
    private Long memberAlarmSettingId;
    private String createdDateTime;
    private String updatedDateTime;
}
