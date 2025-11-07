package com.example.kok.repository;

import com.example.kok.dto.ConsoleInternNoticeDTO;
import com.example.kok.dto.ConsoleInternNoticeRequestDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.mapper.ConsoleInternNoticeMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleInternNoticeDAO {
    private final ConsoleInternNoticeMapper consoleInternMapper;

//    공고 목록(전체)
    public List<ConsoleInternNoticeDTO> findAllByCompany(Long companyId, Criteria criteria, Status status, String keyword) {
        return consoleInternMapper.selectInternByCompany(companyId, criteria, status, keyword);
    }

//    공고 개수
    public int findCountByCompany(Long companyId, Status status, String keyword) {
        return consoleInternMapper.selectCountByCompany(companyId, status, keyword);
    }

//    모집중인 공고 개수
    public int findActiveCountByCompany(Long companyId, Status status, String keyword) {
        return consoleInternMapper.selectCountByCompany(companyId, status, keyword);
    }

//    공고 상태 변경
    public void updateNoticeStatus(Long noticeId, Status status) {
        consoleInternMapper.updateNoticeStatus(noticeId, status);
    }

//    활성화된 공고의 지원자
    public int findRequestCountByCompany(Long companyId) {
        return consoleInternMapper.selectRequestCountByCompany(companyId, true);
    }

//    누적 지원자
    public int findRequestActiveCountByCompany(Long companyId) {
        return consoleInternMapper.selectRequestCountByCompany(companyId, false);
    }

//    공고 등록
    public void createNotice(ConsoleInternNoticeRequestDTO noticeRequestDTO) {
        consoleInternMapper.insertNotice(noticeRequestDTO);
    }

//    직군 등록
    public void createNoticeJobCategory(ConsoleInternNoticeRequestDTO noticeRequestDTO) {
        consoleInternMapper.insertNoticeJobCategory(noticeRequestDTO);
    }

//    공고 상세
    public ConsoleInternNoticeRequestDTO findDetailById(Long id) {
        return consoleInternMapper.selectInternDetailById(id);
    }

//    공고 수정 등록
    public void editNotice(ConsoleInternNoticeRequestDTO noticeRequestDTO) {
        consoleInternMapper.updateNotice(noticeRequestDTO);
    }

//    직군 수정
public void editNoticeJobCategory(ConsoleInternNoticeRequestDTO noticeRequestDTO) {
    consoleInternMapper.updateNoticeJobCategory(noticeRequestDTO);
}

//    공고 수정 상세
    public ConsoleInternNoticeRequestDTO findById(Long id) {
        return consoleInternMapper.selectById(id);
    }

//    지원서 삭제
    public void deleteRequestInternByNoticeId(Long id) {
        consoleInternMapper.deleteRequestInternByNoticeId(id);
    }

//    스크랩 삭제
    public void deleteSaveInternByNoticeId(Long id) {
        consoleInternMapper.deleteSaveInternByNoticeId(id);
    }

//    직군 매핑 삭제
    public void deleteJobCategoryByNoticeId(Long id) {
        consoleInternMapper.deleteJobCategoryByNoticeId(id);
    }

//    공고 삭제
    public void deleteInternNoticeById(Long id) {
        consoleInternMapper.deleteInternNoticeById(id);
    }

//    체험 공고 마감 처리
    public void setNoticeStatusToInactive(){
        consoleInternMapper.updateInternNoticeStatusToInactive();
    }
}