package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.enumeration.Status;

public interface ConsoleExperienceNoticeService {
//    공고 목록
    public ConsoleExperienceNoticeCriteriaDTO getList(Long companyId, int page, Status status, String keyword);

//    공고 상태 변경
    public void updateListStatus(Long noticeId, Status status);

//    공고 상세
    ConsoleExperienceNoticeRequestDTO getExperienceDetail(Long id);

//    공고 등록
    public void createOrEdit(boolean isCreate, ConsoleExperienceNoticeRequestDTO noticeRequestDTO);

//    공고 수정 상세
    ConsoleExperienceNoticeRequestDTO getNotice(Long id);

//    공고 삭제
    public void deleteExperience(Long id);

//    체험 공고 마감 처리
    public void closeNotice();
}
