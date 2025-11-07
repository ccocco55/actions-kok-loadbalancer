package com.example.kok.repository;

import com.example.kok.domain.ConsoleAdNoticeVO;
import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import com.example.kok.mapper.ConsoleAdMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleAdDAO {
    private final ConsoleAdMapper consoleAdMapper;

//    목록(전체)
    public List<ConsoleAdNoticeDTO> findAllByCompany(Long companyId, Criteria criteria, String keyword) {
        return consoleAdMapper.selectAdByCompany(companyId, criteria, keyword);
    }

//    개수
    public int findCountByCompany(Long companyId, RequestStatus status, String keyword) {
        return consoleAdMapper.selectCountByCompany(companyId, status, keyword);
    }

//    광고 승인 상태
    public int findActiveCountByCompany(Long companyId, RequestStatus status, String keyword) {
        return consoleAdMapper.selectCountByCompany(companyId, status, keyword);
    }

//    공고 상태 변경
    public void updateNoticeStatus(Long noticeId, Status status) {
        consoleAdMapper.updateNoticeStatus(noticeId, status);
    }

//    총 금액
    public Long findActiveTotalPriceByCompany(Long companyId) {
        return consoleAdMapper.selectActiveTotalPriceByCompany(companyId);
    }

//    광고 등록
    public void createAdvertisement(ConsoleAdNoticeDTO adNoticeDTO) {
        consoleAdMapper.insertAdvertisement(adNoticeDTO);
    }

//    광고 상세
    public ConsoleAdNoticeDTO findDetailById(Long id) {
        return consoleAdMapper.selectAdDetailById(id);
    }

//    광고 수정 등록
    public void editNotice(ConsoleAdNoticeVO adNoticeVO) {
        consoleAdMapper.updateNotice(adNoticeVO);
    }

//    광고 삭제
    public void deleteAdvertisementById(Long id) {
        consoleAdMapper.deleteById(id);
    }

//    광고 마감 처리
    public void setAdStatusToInactive(){
        consoleAdMapper.updateAdStatusToInactive();
    }

}
