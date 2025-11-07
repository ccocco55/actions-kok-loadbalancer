package com.example.kok.service;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.dto.*;
import com.example.kok.repository.AdminEmployDAO;
import com.example.kok.util.AdminExperienceCriteria;
import com.example.kok.util.AdminExperienceListCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminEmployServiceImpl implements AdminEmployService {
    private final AdminEmployDAO adminEmployDAO;

    @Override
    public AdminInternNoticeListCriteriaDTO getList(int page, String keyword) {
        AdminInternNoticeListCriteriaDTO adminInternNoticeList = new AdminInternNoticeListCriteriaDTO();
        AdminExperienceListCriteria listCriteria = new AdminExperienceListCriteria(page, adminEmployDAO.countAllEmploy(keyword));
        List<AdminInternNoticeListDTO> internNotices = adminEmployDAO.getEmployList(listCriteria, keyword);

        listCriteria.setHasMore(internNotices.size() > listCriteria.getRowCount());
        listCriteria.setHasPreviousPage(page > 1);
        listCriteria.setHasNextPage(page < listCriteria.getRealEnd());

//        11개 가져왔으면, 마지막 1개 삭제
        if(listCriteria.isHasMore()){
            internNotices.remove(internNotices.size()-1);
        }

        adminInternNoticeList.setInternNoticeList(internNotices);
        adminInternNoticeList.setInternListCriteria(listCriteria);
        return adminInternNoticeList;
    }

    @Override
    @Cacheable(value = "adminEmploy", key = "'adminEmploy_' + #id")
    public AdminInternNoticeDetailDTO getDetail(Long id) {

//        인턴공고 - 상세정보
        AdminInternNoticeDetailDTO adminInternNoticeDetailDTO = adminEmployDAO.selectEmploy(id).orElseThrow(PostNotFoundException::new);
        return adminInternNoticeDetailDTO;
    }
}














