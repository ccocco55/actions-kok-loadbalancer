package com.example.kok.service;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.dto.*;
import com.example.kok.repository.AdminExperienceDAO;
import com.example.kok.repository.AdminNoticeDAO;
import com.example.kok.repository.UserDAO;
import com.example.kok.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminServiceImpl implements AdminService {
    private final AdminNoticeDAO adminNoticeDAO;
    private final AdminExperienceDAO adminExperienceDAO;

//    체험
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminExperienceListDTO getExperience(int page, String keyword) {
        AdminExperienceListDTO adminExperienceListDTO = new AdminExperienceListDTO();
        AdminExperienceListCriteria listCriteria = new AdminExperienceListCriteria(page, adminExperienceDAO.adminExperienceSearchCountAll(keyword));
        List<AdminExperienceDTO> experiences = adminExperienceDAO.adminExperienceAll(listCriteria, keyword);

        listCriteria.setHasMore(experiences.size() > listCriteria.getRowCount());
        listCriteria.setHasPreviousPage(page > 1);
        listCriteria.setHasNextPage(page < listCriteria.getRealEnd());

//        11개 가져왔으면, 마지막 1개 삭제
        if(listCriteria.isHasMore()){
            experiences.remove(experiences.size()-1);
        }

        adminExperienceListDTO.setListCriteria(listCriteria);
        adminExperienceListDTO.setExperienceList(experiences);

        return adminExperienceListDTO;
    }

//    체험 상세
    @Override
    @Cacheable(value = "adminExperience", key = "'adminExperience_' + #id")
    public AdminExperienceDTO getExperienceDetail(Long id) {

//        체험공고 상세정보
        AdminExperienceDTO adminExperienceDTO = adminExperienceDAO.selectAdminExperience(id);

        log.info("체험 상세1: {}", adminExperienceDTO);
        return adminExperienceDTO;
    }

//    공지 등록
    @Override
    public void write(AdminNoticeDTO adminNoticeDTO) {
        AdminNoticeVO adminNoticeVO = toVO(adminNoticeDTO);
        adminNoticeDAO.insert(adminNoticeVO);
        adminNoticeDTO.setId(adminNoticeVO.getId());
    }

//    공지 상세
    @Override
    @Cacheable(value = "adminNotice", key = "'adminNotice_' + #id")
    public AdminNoticeDTO getNotice (Long id) {
        AdminNoticeDTO foundNotice = adminNoticeDAO.selectNotice(id).orElseThrow(PostNotFoundException::new);
        foundNotice.setCreatedDateTime(DateUtils.getCreatedDate(foundNotice.getCreatedDateTime()));
        foundNotice.setUpdatedDateTime(DateUtils.getCreatedDate(foundNotice.getUpdatedDateTime()));
        return foundNotice;
    }

//    공지 목록
    @Override
    public AdminNoticeCriteriaDTO getList(int page) {
        AdminNoticeCriteriaDTO adminNoticeCriteriaDTO = new AdminNoticeCriteriaDTO();
        Criteria criteria = new Criteria(page, adminNoticeDAO.countAll());
        List<AdminNoticeDTO> noticeList = adminNoticeDAO.selectAll(criteria);
        noticeList.forEach((notice) -> {
            String relativeDate = DateUtils.getCreatedDate(notice.getCreatedDateTime());
            String[] dateTime = relativeDate.split(" ");
            notice.setRelativeDate(dateTime[0]);
        });

        criteria.setHasMore(noticeList.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

        //  11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            noticeList.remove(noticeList.size() - 1);
        }

        adminNoticeCriteriaDTO.setNoticeList(noticeList);
        adminNoticeCriteriaDTO.setNoticeCriteria(criteria);

        return adminNoticeCriteriaDTO;
    }

//    공지 수정
    @Override
    @CachePut(value = "adminNotice", key = "'adminNotice_' + #adminNoticeDTO.id")
    public AdminNoticeDTO update(AdminNoticeDTO adminNoticeDTO) {
        AdminNoticeVO adminNoticeVO = toVO(adminNoticeDTO);
        adminNoticeDAO.updateNotice(adminNoticeVO);
        adminNoticeDTO.setId(adminNoticeVO.getId());

        return adminNoticeDTO;
    }

//    공지 삭제
    @Override
    @CacheEvict(value = "adminNotice", key = "'adminNotice_' + #id")
    public void delete(Long id) {
        adminNoticeDAO.deleteNotice(id);
    }

}
