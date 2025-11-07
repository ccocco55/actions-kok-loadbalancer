package com.example.kok.dto;

import com.example.kok.util.AdminExperienceCriteria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminInternNoticeDetailCriteriaDTO {
//    private AdminInternNoticeDetailDTO internNoticeDetail;
    private List<AdminInternNoticeDetailRequestDTO> internNoticeDetailRequests;

    private AdminExperienceCriteria internDetailCriteria;
}
