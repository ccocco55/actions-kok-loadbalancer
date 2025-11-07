package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class PostLikeDTO {
    private long id;
    private long memberId;
    private long postId;
    private long memberAlarmSettingId;
    private String createdDateTime;
    private String updatedDateTime;
}
