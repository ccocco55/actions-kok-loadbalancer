package com.example.kok.service;

import com.example.kok.dto.ConsoleInternApplicantCriteriaDTO;
import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.ConsoleInternNoticeDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.ConsoleInternDetailDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsoleInternDetailServiceImpl implements ConsoleInternDetailService {
    private final ConsoleInternDetailDAO consoleInternDAO;

    @Override
    public ConsoleInternNoticeDTO getDetail(Long internNoticeId) {
        return consoleInternDAO.findInternDetailByCompany(internNoticeId);
    }

    @Override
    public ConsoleInternApplicantCriteriaDTO getApplicateList(Long companyId, int page, RequestStatus status) {
        ConsoleInternApplicantCriteriaDTO consoleInternApplicantCriteriaDTO = new ConsoleInternApplicantCriteriaDTO();

        int totalCount = consoleInternDAO.findCountByCompany(companyId, status);

        Criteria criteria = new Criteria(page, totalCount);

        List<ConsoleInternApplicantDTO> notices = consoleInternDAO.findAllByCompany(companyId, status, criteria);

        criteria.setHasMore(notices.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            notices.remove(notices.size() - 1);
        }

        consoleInternApplicantCriteriaDTO.setTotalCount(totalCount);
        consoleInternApplicantCriteriaDTO.setApplicantLists(notices);
        consoleInternApplicantCriteriaDTO.setCriteria(criteria);

        return consoleInternApplicantCriteriaDTO;
    }
}
