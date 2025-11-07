package com.example.kok.repository;

import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.dto.AdminNoticeDTO;
import com.example.kok.mapper.AdminNoticeMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdminNoticeDAO {
    private final AdminNoticeMapper adminNoticeMapper;

//    등록
    public void insert (AdminNoticeVO adminNoticeVO){
        adminNoticeMapper.insertNotice(adminNoticeVO);
    }

//    상세
    public Optional<AdminNoticeDTO> selectNotice (Long id){
        return adminNoticeMapper.selectNoticeFromId(id);
    }

//    목록
    public List<AdminNoticeDTO> selectAll (Criteria criteria) {
        return adminNoticeMapper.selectNoticeAll(criteria);
    }

//    전체 개수
    public int countAll () {
        return adminNoticeMapper.countNoticeAll();
    }

//    수정
    public void updateNotice (AdminNoticeVO adminNoticeVO){
        adminNoticeMapper.updateNoticeFromId(adminNoticeVO);
    }

//    삭제
    public void deleteNotice (Long id){
        adminNoticeMapper.deleteNoticeFromId(id);
    }
}
