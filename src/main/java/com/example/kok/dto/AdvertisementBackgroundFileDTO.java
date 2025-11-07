package com.example.kok.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="fileId")
@NoArgsConstructor
public class AdvertisementBackgroundFileDTO {
    private Long fileId;
    private Long advertisementId;

    private String fileOriginName;
    private String fileName;
    private String fileSize;
    private String filePath;
    private String fileContentType;
    private String createdDatetime;
    private String updatedDatetime;

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // this 객체를 JSON 문자열로 변환
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return super.toString();
        }
    }
}
