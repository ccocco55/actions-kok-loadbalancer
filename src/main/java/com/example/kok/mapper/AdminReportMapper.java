package com.example.kok.mapper;

import com.example.kok.dto.AdminReportDTO;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminReportMapper {
//    신고 게시글 목록
    public List<AdminReportDTO> selectReportAll(Criteria criteria);

//    신고 게시글 개수
    public int selectReportCount();

//    신고 게시글 상세
    public AdminReportDTO selectReportDetail(Long id);

//    신고 게시글 삭제
    public void deleteReportPost(Long id);
}
