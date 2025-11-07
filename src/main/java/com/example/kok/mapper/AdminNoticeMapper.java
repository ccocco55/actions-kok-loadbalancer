package com.example.kok.mapper;

import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.dto.AdminNoticeDTO;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminNoticeMapper {
//    공지사항 등록
    public void insertNotice (AdminNoticeVO adminNoticeVO);

//    공지사항 선택
    public Optional<AdminNoticeDTO> selectNoticeFromId (Long id);

//    공지사항 목록
    public List<AdminNoticeDTO> selectNoticeAll (Criteria criteria);

//    전체 개수
    public int countNoticeAll ();

//    공지사항 수정
    public void updateNoticeFromId(AdminNoticeVO adminNoticeVO);

//    공지사항 삭제
    public void deleteNoticeFromId(Long id);
}
