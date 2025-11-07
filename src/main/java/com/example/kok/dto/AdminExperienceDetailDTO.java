package com.example.kok.dto;

import com.example.kok.util.AdminExperienceCriteria;
import com.example.kok.util.AdminExperienceListCriteria;
import com.example.kok.util.AdminExperienceRequestCriteria;
import com.example.kok.util.Criteria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Getter
@Setter
@ToString
public class AdminExperienceDetailDTO {
//    private AdminExperienceDTO experience;
    private List<UserRequestExperienceDTO> userRequestExperience;
    private List<UserEvaluationDTO> userEvaluation;

    private AdminExperienceRequestCriteria adminExperienceRequestCriteria;
    private AdminExperienceCriteria adminExperienceCriteria;
}
