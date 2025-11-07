package com.example.kok.mapper;

import com.example.kok.dto.ConsoleExperienceNoticeDTO;
import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleExperienceNoticeMapper {

//    공고 목록
    public List<ConsoleExperienceNoticeDTO> selectExperienceByCompany(@Param("companyId") Long companyId,
                                                                      @Param("criteria") Criteria criteria,
                                                                      @Param("status") Status status,
                                                                      @Param("keyword") String keyword);

//    공고 개수
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
    public void insertNotice(ConsoleExperienceNoticeRequestDTO noticeRequestDTO);

//    직군 등록
    public void insertNoticeJobCategory(ConsoleExperienceNoticeRequestDTO noticeRequestDTO);

//    공고 상세
    public ConsoleExperienceNoticeRequestDTO selectExperienceDetailById(@Param("id") Long id);

//    공고 수정 등록
    public void updateNotice(ConsoleExperienceNoticeRequestDTO noticeRequestDTO);

//    공고 수정 상세
    ConsoleExperienceNoticeRequestDTO selectById(@Param("id") Long id);

//    직군 수정
    public void updateNoticeJobCategory(ConsoleExperienceNoticeRequestDTO noticeRequestDTO);

//    지원서 삭제
    public void deleteRequestExperienceByNoticeId(Long id);

//    스크랩 삭제
    public void deleteSaveExperienceByNoticeId(Long id);

//    직군 매핑 삭제
    public void deleteJobCategoryByNoticeId(Long id);

//    공고 삭제
    public void deleteExperienceNoticeById(Long id);

//    체험 공고 마감 처리
    public void updateExperienceNoticeStatusToInactive();
}
