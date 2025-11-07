package com.example.kok.controller;

import com.example.kok.dto.AdminExperienceDetailDTO;
import com.example.kok.dto.UserEvaluationDTO;
import com.example.kok.dto.UserRequestExperienceDTO;
import com.example.kok.repository.AdminExperienceDAO;
import com.example.kok.util.AdminExperienceCriteria;
import com.example.kok.util.AdminExperienceRequestCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminExperienceServiceImpl implements AdminExperienceService {
    private final AdminExperienceDAO adminExperienceDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminExperienceDetailDTO getDetailLists(int page, Long id) {
        AdminExperienceDetailDTO adminExperienceDetailDTO = new AdminExperienceDetailDTO();

//        신청자 내역
        AdminExperienceRequestCriteria requestCriteria = new AdminExperienceRequestCriteria(page, adminExperienceDAO.countRequestUser(id));
        List<UserRequestExperienceDTO> requestExperienceList = adminExperienceDAO.requestUser(requestCriteria, id);

        requestCriteria.setHasMore(requestExperienceList.size() > requestCriteria.getRowCount());
        requestCriteria.setHasPreviousPage(page > 1);
        requestCriteria.setHasNextPage(5 < requestExperienceList.size());

//        6개 가져왔으면, 마지막 1개 삭제
        if(requestCriteria.isHasMore()){
            requestExperienceList.remove(requestExperienceList.size()-1);
        }
        adminExperienceDetailDTO.setAdminExperienceRequestCriteria(requestCriteria);

        log.info("체험 상세 목록1: {}", requestExperienceList);

//        체험 회원 평가
        AdminExperienceCriteria experienceCriteria = new AdminExperienceCriteria(page, adminExperienceDAO.countUserEvaluation(id));
        List<UserEvaluationDTO> userEvaluationList = adminExperienceDAO.userEvaluation(experienceCriteria, id);

        experienceCriteria.setHasMore(userEvaluationList.size() > experienceCriteria.getRowCount());
        experienceCriteria.setHasPreviousPage(page > 1);
        experienceCriteria.setHasNextPage(5 < userEvaluationList.size());

//        6개 가져왔으면, 마지막 1개 삭제
        if(experienceCriteria.isHasMore()){
            userEvaluationList.remove(userEvaluationList.size()-1);
        }
        adminExperienceDetailDTO.setAdminExperienceCriteria(experienceCriteria);

        log.info("체험 상세 목록2: {}", userEvaluationList);

        adminExperienceDetailDTO.setUserRequestExperience(requestExperienceList);
        adminExperienceDetailDTO.setUserEvaluation(userEvaluationList);
        return adminExperienceDetailDTO;
    }
}
