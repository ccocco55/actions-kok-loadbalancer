package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="fileId")
public class MemberStorageFileDTO {
    private Long fileId;
    private Long memberId;
    private String createdDateTime;
    private String updatedDateTime;
}
