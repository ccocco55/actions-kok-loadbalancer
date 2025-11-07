package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class FollowDTO{
    private Long id;
    private Long memberId;
    private Long companyId;
    private Status followStatus;
}
