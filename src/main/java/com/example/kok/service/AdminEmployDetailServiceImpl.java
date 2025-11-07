package com.example.kok.service;

import com.example.kok.dto.AdminInternNoticeDetailCriteriaDTO;
import com.example.kok.dto.AdminInternNoticeDetailRequestDTO;
import com.example.kok.repository.AdminEmployDAO;
import com.example.kok.util.AdminExperienceCriteria;
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
public class AdminEmployDetailServiceImpl implements AdminEmployDetailService {
    private final AdminEmployDAO adminEmployDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminInternNoticeDetailCriteriaDTO getDetailList(int page, Long id) {
        AdminInternNoticeDetailCriteriaDTO adminInternNoticeDetail = new AdminInternNoticeDetailCriteriaDTO();

        //        인턴공고 - 신청자 정보
        AdminExperienceCriteria requestCriteria = new AdminExperienceCriteria(page, adminEmployDAO.countRequest(id));
        List<AdminInternNoticeDetailRequestDTO> internRequests = adminEmployDAO.selectRequest(requestCriteria, id);

        requestCriteria.setHasMore(internRequests.size() > requestCriteria.getRowCount());
        requestCriteria.setHasPreviousPage(page > 1);
        requestCriteria.setHasNextPage(5 < internRequests.size());

//        6개 가져왔으면, 마지막 1개 삭제
        if(requestCriteria.isHasMore()){
            internRequests.remove(internRequests.size()-1);
        }

        adminInternNoticeDetail.setInternNoticeDetailRequests(internRequests);
        adminInternNoticeDetail.setInternDetailCriteria(requestCriteria);
        return adminInternNoticeDetail;
    }
}
