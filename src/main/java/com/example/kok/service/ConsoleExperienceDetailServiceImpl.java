package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.ConsoleExperienceDetailDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsoleExperienceDetailServiceImpl implements ConsoleExperienceDetailService {
    private final ConsoleExperienceDetailDAO consoleExperienceDAO;

    @Override
    public ConsoleExperienceNoticeDTO getDetail(Long experienceNoticeId) {
        return consoleExperienceDAO.findExperienceDetailByCompany(experienceNoticeId);
    }

    @Override
    public ConsoleExperienceApplicantCriteriaDTO getApplicateList(Long companyId, int page, RequestStatus status) {
        ConsoleExperienceApplicantCriteriaDTO consoleExperienceApplicantCriteriaDTO = new ConsoleExperienceApplicantCriteriaDTO();

        int totalCount = consoleExperienceDAO.findCountByCompany(companyId, status);

        Criteria criteria = new Criteria(page, totalCount);

        List<ConsoleExperienceApplicantDTO> notices = consoleExperienceDAO.findAllByCompany(companyId, status, criteria);

        criteria.setHasMore(notices.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            notices.remove(notices.size() - 1);
        }

        consoleExperienceApplicantCriteriaDTO.setTotalCount(totalCount);
        consoleExperienceApplicantCriteriaDTO.setApplicantLists(notices);
        consoleExperienceApplicantCriteriaDTO.setCriteria(criteria);

        return consoleExperienceApplicantCriteriaDTO;
    }
}
