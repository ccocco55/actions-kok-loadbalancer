package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class BannerFileDTO {
    private Long id;
    private String bannerFileOriginName;
    private String bannerFileName;
    private String bannerFilePath;
    private String bannerFileSize;
    private String bannerFileContentType;
    private String createdDateTime;
    private String updatedDateTime;

    private String relativeDate;
}
