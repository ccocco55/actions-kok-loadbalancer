package com.example.kok.service;

import com.example.kok.dto.ConsoleInternNoticeRequestDTO;
import com.example.kok.dto.ConsoleInternNoticeCriteriaDTO;
import com.example.kok.enumeration.Status;

public interface ConsoleInternNoticeService {
//    공고 목록
    public ConsoleInternNoticeCriteriaDTO getList(Long companyId, int page, Status status, String keyword);

//    공고 상태 변경
    public void updateListStatus(Long noticeId, Status status);

//    공고 상세
    ConsoleInternNoticeRequestDTO getDetail(Long id);

//    공고 등록
    public void createOrEdit(boolean isCreate, ConsoleInternNoticeRequestDTO noticeRequestDTO);

//    공고 수정 상세
    ConsoleInternNoticeRequestDTO getNotice(Long id);

//    공고 삭제
    public void deleteIntern(Long id);

//    인턴 공고 마감 처리
    public void closeNotice();
}
