package com.example.kok.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of="fileId")
public class ConsoleAdNoticeFileDTO {
    private long fileId;
    private long advertisementId;
    private String fileOriginName;
    private String fileName;
    private String filePath;
    private String fileSize;
    private String fileContentType;
    private String createdDatetime;
    private String updatedDatetime;

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return super.toString();
        }
    }
}
