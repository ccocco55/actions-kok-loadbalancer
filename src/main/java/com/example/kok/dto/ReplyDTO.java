package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class ReplyDTO {
    private long id;
    private String replyContent;
    private Status replyStatus;
    private long memberId;
    private long commentId;
    private long memberAlarmSettingId;
    private String createdDateTime;
    private String updatedDateTime;

    private String relativeDate;
    private int likesCount;
    private String userName;
    private String jobName;
    private String memberProfileUrl;
    private boolean liked;
    private boolean owner;
    private String snsProfile;
}
