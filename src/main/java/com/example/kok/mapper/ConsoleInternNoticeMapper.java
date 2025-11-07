package com.example.kok.mapper;

import com.example.kok.dto.ConsoleInternNoticeRequestDTO;
import com.example.kok.dto.ConsoleInternNoticeDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleInternNoticeMapper {

//    공고 목록
    public List<ConsoleInternNoticeDTO> selectInternByCompany(@Param("companyId") Long companyId,
                                                              @Param("criteria") Criteria criteria,
                                                              @Param("status") Status status,
                                                              @Param("keyword") String keyword);

//    공고 개수(전체, 모집중)
    public int selectCountByCompany(@Param("companyId") Long companyId,
                                    @Param("status") Status status,
                                    @Param("keyword") String keyword);

//    공고 상태 변경
    public void updateNoticeStatus(@Param("noticeId") Long noticeId,
                                 @Param("status") Status status);

//    지원자개수(활성화 공고 지원자, 누적 지원자)
    public int selectRequestCountByCompany(@Param("companyId") Long companyId,
                                           @Param("active") boolean active);

//    공고 등록
    public void insertNotice(ConsoleInternNoticeRequestDTO noticeRequestDTO);

//    직군 등록
    public void insertNoticeJobCategory(ConsoleInternNoticeRequestDTO noticeRequestDTO);

//    공고 상세
    public ConsoleInternNoticeRequestDTO selectInternDetailById(@Param("id") Long id);

//    공고 수정 등록
    public void updateNotice(ConsoleInternNoticeRequestDTO noticeRequestDTO);

//    공고 수정 상세
    ConsoleInternNoticeRequestDTO selectById(@Param("id") Long id);

//    직군 수정
    public void updateNoticeJobCategory(ConsoleInternNoticeRequestDTO noticeRequestDTO);

//    지원서 삭제
    public void deleteRequestInternByNoticeId(Long id);

//    스크랩 삭제
    public void deleteSaveInternByNoticeId(Long id);

//    직군 매핑 삭제
    public void deleteJobCategoryByNoticeId(Long id);

//    공고 삭제
    public void deleteInternNoticeById(Long id);

//    체험 공고 마감 처리
    public void updateInternNoticeStatusToInactive();
}
