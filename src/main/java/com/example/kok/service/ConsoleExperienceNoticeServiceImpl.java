package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceNoticeDTO;
import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.mapper.MemberAlarmSettingMapper;
import com.example.kok.repository.ConsoleExperienceNoticeDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsoleExperienceNoticeServiceImpl implements ConsoleExperienceNoticeService {
    private final ConsoleExperienceNoticeDAO consoleExperienceDAO;
    private final memberAlarmService memberAlarmService;

    @Override
    public ConsoleExperienceNoticeCriteriaDTO getList(Long companyId, int page, Status status, String keyword) {
        ConsoleExperienceNoticeCriteriaDTO consoleExperienceNoticeCriteriaDTO = new ConsoleExperienceNoticeCriteriaDTO();

        int totalCount = consoleExperienceDAO.findCountByCompany(companyId, status, keyword);
        int activeCount = consoleExperienceDAO.findActiveCountByCompany(companyId, Status.ACTIVE, keyword);
        int requestCount = consoleExperienceDAO.findRequestCountByCompany(companyId);
        int activeRequestCount = consoleExperienceDAO.findRequestActiveCountByCompany(companyId);

        Criteria criteria = new Criteria(page, totalCount);

        List<ConsoleExperienceNoticeDTO> notices = consoleExperienceDAO.findAllByCompany(companyId, criteria, status, keyword);

        criteria.setHasMore(notices.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            notices.remove(notices.size() - 1);
        }

        consoleExperienceNoticeCriteriaDTO.setExperienceLists(notices);
        consoleExperienceNoticeCriteriaDTO.setCriteria(criteria);
        consoleExperienceNoticeCriteriaDTO.setTotalCount(totalCount);
        consoleExperienceNoticeCriteriaDTO.setActiveTotalCount(activeCount);
        consoleExperienceNoticeCriteriaDTO.setTotalRequestCount(requestCount);
        consoleExperienceNoticeCriteriaDTO.setActiveRequestCount(activeRequestCount);

        return consoleExperienceNoticeCriteriaDTO;
    }

//    공고 상태 변경
    @Override
    public void updateListStatus(Long noticeId, Status status) {
        consoleExperienceDAO.updateNoticeStatus(noticeId, status);
    }

//    공고 상세
    @Override
    public ConsoleExperienceNoticeRequestDTO getExperienceDetail(Long id) {
        return consoleExperienceDAO.findDetailById(id);
    }

//    공고 등록 밑 수정
    @Override
    @Transactional
    public void createOrEdit(boolean isCreate, ConsoleExperienceNoticeRequestDTO noticeRequestDTO) {
        if (isCreate) {
            //        공고 등록
            consoleExperienceDAO.createNotice(noticeRequestDTO);

            //        직군 등록
            consoleExperienceDAO.createNoticeJobCategory(noticeRequestDTO);

            //        알림
            Long noticeId = noticeRequestDTO.getId();
            memberAlarmService.experienceAlarm(noticeId);
        } else {
            //        공고 수정
            consoleExperienceDAO.editNotice(noticeRequestDTO);

            //        직군 수정
            consoleExperienceDAO.editNoticeJobCategory(noticeRequestDTO);
        }
    }

//    공고 수정 상세
    @Override
    public ConsoleExperienceNoticeRequestDTO getNotice(Long id) {
        return consoleExperienceDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteExperience(Long id) {
        consoleExperienceDAO.deleteRequestExperienceByNoticeId(id);
        consoleExperienceDAO.deleteSaveExperienceByNoticeId(id);
        consoleExperienceDAO.deleteJobCategoryByNoticeId(id);
        consoleExperienceDAO.deleteExperienceNoticeById(id);
    }

//    체험 공고 마감 처리
    public void closeNotice(){
        consoleExperienceDAO.setNoticeStatusToInactive();
    }

}
