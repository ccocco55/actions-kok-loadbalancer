package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class AdvertisementDTO {
    private long id;
    private String advertisementMainText;
    private String advertisementSubText;
    private RequestStatus advertisementStatus;
    private String advertiseStartDatetime;
    private String advertiseEndDatetime;
    private long companyId;
    private String createdDateTime;
    private String updatedDateTime;

    private String filePath;
    private String companyName;
    private String companyUrl;
    private String userEmail;
    private String userPhone;

    private Long fileId;
    private Long advertisementId;
    private List<AdvertisementBackgroundFileDTO> advertisementBackgroundFiles;
}
