package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class JobCategoryDTO {
    private Long id;
    private String jobName;
    private String createdDateTime;
    private String updatedDateTime;
}
