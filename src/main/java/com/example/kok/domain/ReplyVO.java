package com.example.kok.domain;

import com.example.kok.audit.Period;
import com.example.kok.enumeration.Status;
import com.example.kok.enumeration.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@SuperBuilder
public class ReplyVO extends Period{
    private long id;
    private String replyContent;
    private Status replyStatus;
    private long memberId;
    private long commentId;
    private long memberAlarmSettingId;
}
