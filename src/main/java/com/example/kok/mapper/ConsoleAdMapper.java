package com.example.kok.mapper;

import com.example.kok.domain.ConsoleAdNoticeVO;
import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleAdMapper {

//    목록
    public List<ConsoleAdNoticeDTO> selectAdByCompany(@Param("companyId") Long companyId,
                                                          @Param("criteria") Criteria criteria,
                                                          @Param("keyword") String keyword);

//    개수
    public int selectCountByCompany(@Param("companyId") Long companyId,
                                    @Param("status") RequestStatus status,
                                    @Param("keyword") String keyword);

//    총 금액
    Long selectActiveTotalPriceByCompany(@Param("companyId") Long companyId);

//    등록
    public void insertAdvertisement(ConsoleAdNoticeDTO adNoticeDTO);

//    광고 상세
    public ConsoleAdNoticeDTO selectAdDetailById(@Param("id") Long id);

//    광고 수정
    public void updateNotice(ConsoleAdNoticeVO adNoticeVO);

//    공고 상태 변경
    public void updateNoticeStatus(@Param("noticeId") Long noticeId,
                                   @Param("status") Status status);

//    광고 삭제
    public void deleteById(@Param("id") Long id);

//    광고 마감 처리
    public void updateAdStatusToInactive();
}
