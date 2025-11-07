package com.example.kok.dto;

import com.example.kok.audit.Period;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class FileDTO{
    private Long id;
    private String fileOriginName;
    private String fileName;
    private String fileSize;
    private String filePath;
    private String fileContentType;
    private String createdDatetime;
    private String updatedDatetime;
}
