package com.example.kok.mapper;

import com.example.kok.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommunityReportMapper {

//    게시글 신고
    public void insertReport(ReportDTO reportDTO);

//    신고 여부 확인
    public boolean existsReport(@Param("postId") Long postId, @Param("memberId") Long memberId);
}
