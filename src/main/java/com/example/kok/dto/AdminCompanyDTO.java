package com.example.kok.dto;


import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="userId")
@NoArgsConstructor
public class AdminCompanyDTO {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String companyName;
    private String companyUrl;
    private String jobName;
    private int followCount;
    private String companyScaleName;
    private List<InternNoticeDTO> internNoticeDTO;
    private int internNoticeCount;
    private List<ExperienceNoticeDTO> experienceNoticeDTO;
    private int experienceNoticeCount;
}
