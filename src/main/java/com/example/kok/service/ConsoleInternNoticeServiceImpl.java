package com.example.kok.service;

import com.example.kok.dto.ConsoleInternNoticeCriteriaDTO;
import com.example.kok.dto.ConsoleInternNoticeDTO;
import com.example.kok.dto.ConsoleInternNoticeRequestDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.repository.ConsoleInternNoticeDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsoleInternNoticeServiceImpl implements ConsoleInternNoticeService {
    private final ConsoleInternNoticeDAO consoleInternDAO;

    @Override
    public ConsoleInternNoticeCriteriaDTO getList(Long companyId, int page, Status status, String keyword) {
        ConsoleInternNoticeCriteriaDTO consoleInternNoticeCriteriaDTO = new ConsoleInternNoticeCriteriaDTO();

        int totalCount = consoleInternDAO.findCountByCompany(companyId, status, keyword);
        int activeCount = consoleInternDAO.findActiveCountByCompany(companyId, Status.ACTIVE, keyword);
        int requestCount = consoleInternDAO.findRequestCountByCompany(companyId);
        int activeRequestCount = consoleInternDAO.findRequestActiveCountByCompany(companyId);

        Criteria criteria = new Criteria(page, totalCount);

        List<ConsoleInternNoticeDTO> notices = consoleInternDAO.findAllByCompany(companyId, criteria, status, keyword);

        criteria.setHasMore(notices.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            notices.remove(notices.size() - 1);
        }

        consoleInternNoticeCriteriaDTO.setInternLists(notices);
        consoleInternNoticeCriteriaDTO.setCriteria(criteria);
        consoleInternNoticeCriteriaDTO.setTotalCount(totalCount);
        consoleInternNoticeCriteriaDTO.setActiveTotalCount(activeCount);
        consoleInternNoticeCriteriaDTO.setTotalRequestCount(requestCount);
        consoleInternNoticeCriteriaDTO.setActiveRequestCount(activeRequestCount);

        return consoleInternNoticeCriteriaDTO;
    }

//    공고 상태 변경
    @Override
    public void updateListStatus(Long noticeId, Status status) {
        consoleInternDAO.updateNoticeStatus(noticeId, status);
    }

//    공고 상세
    @Override
    public ConsoleInternNoticeRequestDTO getDetail(Long id) {
        return consoleInternDAO.findDetailById(id);
    }

//    공고 등록 밑 수정
    @Override
    @Transactional
    public void createOrEdit(boolean isCreate, ConsoleInternNoticeRequestDTO noticeRequestDTO) {
        if (isCreate) {
//        공고 등록
        consoleInternDAO.createNotice(noticeRequestDTO);

//        직군 등록
        consoleInternDAO.createNoticeJobCategory(noticeRequestDTO);
        } else {
            //        공고 수정
            consoleInternDAO.editNotice(noticeRequestDTO);

            //        직군 수정
            consoleInternDAO.editNoticeJobCategory(noticeRequestDTO);
        }
    }

//    공고 수정 상세
    @Override
    public ConsoleInternNoticeRequestDTO getNotice(Long id) {
        return consoleInternDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteIntern(Long id) {
        consoleInternDAO.deleteRequestInternByNoticeId(id);
        consoleInternDAO.deleteSaveInternByNoticeId(id);
        consoleInternDAO.deleteJobCategoryByNoticeId(id);
        consoleInternDAO.deleteInternNoticeById(id);
    }

//    체험 공고 마감 처리
    public void closeNotice(){
        consoleInternDAO.setNoticeStatusToInactive();
    }

}
