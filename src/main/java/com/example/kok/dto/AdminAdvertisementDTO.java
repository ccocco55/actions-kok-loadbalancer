package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class AdminAdvertisementDTO {
    private Long id;
    private String advertisementMainText;
    private String advertisementSubText;
    private RequestStatus advertisementRequestStatus;
    private String advertiseStartDatetime;
    private String advertiseEndDatetime;
    private Long companyId;
    private String createdDateTime;
    private String updatedDateTime;

    private String companyName;
    private String companyUrl;
    private String userEmail;
    private String userPhone;

    private Long fileId;
    private Long advertisementId;
    private List<AdvertisementBackgroundFileDTO> advertisementBackgroundFiles;
}
