package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import com.example.kok.enumeration.UserRole;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="userId")
public class UserJobCategoryDTO {
    private long userId;
    private long jobCategoryId;
}
