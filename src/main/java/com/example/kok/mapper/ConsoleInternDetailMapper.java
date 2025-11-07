package com.example.kok.mapper;

import com.example.kok.dto.ConsoleInternApplicantDTO;
import com.example.kok.dto.ConsoleInternNoticeDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleInternDetailMapper {

//    공고 상세 내용
    ConsoleInternNoticeDTO selectInternDatailByCompany(@Param("internNoticeId") Long internNoticeId);

//    지원 목록
    public List<ConsoleInternApplicantDTO> selectInternApplicantByCompany(@Param("internNoticeId") Long internNoticeId,
                                                                                  @Param("status") RequestStatus status,
                                                                                  @Param("criteria") Criteria criteria);

//    지원 목록 개수
    public int selectInternApplicantCountByCompany(@Param("internNoticeId") Long internNoticeId,
                                                       @Param("status") RequestStatus status);

}
