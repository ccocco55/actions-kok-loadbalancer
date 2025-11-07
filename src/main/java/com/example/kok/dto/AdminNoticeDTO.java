package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class AdminNoticeDTO{
    private Long id;
    private String adminNoticeTitle;
    private String adminNoticeContent;
    private Status noticeStatus;
    private String relativeDate;
    private String createdDateTime;
    private String updatedDateTime;
}
