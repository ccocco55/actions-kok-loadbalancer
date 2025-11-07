package com.example.kok.repository;

import com.example.kok.dto.ConsoleExperienceNoticeDTO;
import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.mapper.ConsoleExperienceNoticeMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleExperienceNoticeDAO {
    private final ConsoleExperienceNoticeMapper consoleExperienceMapper;

//    공고 목록
    public List<ConsoleExperienceNoticeDTO> findAllByCompany(Long companyId, Criteria criteria, Status status, String keyword) {
        return consoleExperienceMapper.selectExperienceByCompany(companyId, criteria, status, keyword);
    }

//    공고 개수
    public int findCountByCompany(Long companyId, Status status, String keyword) {
        return consoleExperienceMapper.selectCountByCompany(companyId, status, keyword);
    }

//    모집중인 공고 개수
    public int findActiveCountByCompany(Long companyId, Status status, String keyword) {
        return consoleExperienceMapper.selectCountByCompany(companyId, status, keyword);
    }

//    공고 상태 변경
    public void updateNoticeStatus(Long noticeId, Status status) {
        consoleExperienceMapper.updateNoticeStatus(noticeId, status);
    }

//    활성화된 공고의 지원자
    public int findRequestCountByCompany(Long companyId) {
        return consoleExperienceMapper.selectRequestCountByCompany(companyId, true);
    }

//    누적 지원자
    public int findRequestActiveCountByCompany(Long companyId) {
        return consoleExperienceMapper.selectRequestCountByCompany(companyId, false);
    }

//    공고 등록
    public void createNotice(ConsoleExperienceNoticeRequestDTO noticeRequestDTO) {
        consoleExperienceMapper.insertNotice(noticeRequestDTO);
    }

//    직군 등록
    public void createNoticeJobCategory(ConsoleExperienceNoticeRequestDTO noticeRequestDTO) {
        consoleExperienceMapper.insertNoticeJobCategory(noticeRequestDTO);
    }

//    공고 상세
    public ConsoleExperienceNoticeRequestDTO findDetailById(Long id) {
        return consoleExperienceMapper.selectExperienceDetailById(id);
    }

//    공고 수정 등록
    public void editNotice(ConsoleExperienceNoticeRequestDTO noticeRequestDTO) {
        consoleExperienceMapper.updateNotice(noticeRequestDTO);
    }

//    직군 수정
    public void editNoticeJobCategory(ConsoleExperienceNoticeRequestDTO noticeRequestDTO) {
        consoleExperienceMapper.updateNoticeJobCategory(noticeRequestDTO);
    }

//    공고 수정 상세
    public ConsoleExperienceNoticeRequestDTO findById(Long id) {
        return consoleExperienceMapper.selectById(id);
    }

//    지원서 삭제
    public void deleteRequestExperienceByNoticeId(Long id) {
        consoleExperienceMapper.deleteRequestExperienceByNoticeId(id);
    }

//    스크랩 삭제
    public void deleteSaveExperienceByNoticeId(Long id) {
        consoleExperienceMapper.deleteSaveExperienceByNoticeId(id);
    }

//    직군 매핑 삭제
    public void deleteJobCategoryByNoticeId(Long id) {
        consoleExperienceMapper.deleteJobCategoryByNoticeId(id);
    }

//    공고 삭제
    public void deleteExperienceNoticeById(Long id) {
        consoleExperienceMapper.deleteExperienceNoticeById(id);
    }

//    체험 공고 마감 처리
    public void setNoticeStatusToInactive(){
        consoleExperienceMapper.updateExperienceNoticeStatusToInactive();
    }
}
