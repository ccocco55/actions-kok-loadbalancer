package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class ReportDTO {
    private long id;
    private long postId;
    private long memberId;
}
