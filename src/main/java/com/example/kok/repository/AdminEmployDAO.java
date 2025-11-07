package com.example.kok.repository;

import com.example.kok.dto.AdminInternNoticeDetailDTO;
import com.example.kok.dto.AdminInternNoticeDetailRequestDTO;
import com.example.kok.dto.AdminInternNoticeListDTO;
import com.example.kok.mapper.AdminEmployMapper;
import com.example.kok.util.AdminExperienceCriteria;
import com.example.kok.util.AdminExperienceListCriteria;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdminEmployDAO {
    private final AdminEmployMapper adminEmployMapper;

//    인턴 공고 목록
    public List<AdminInternNoticeListDTO> getEmployList(@Param("criteria") AdminExperienceListCriteria criteria,
                                                        @Param("keyword") String keyword){
        return adminEmployMapper.selectEmployAll(criteria, keyword);
    }

//    전체 개수
    public int countAllEmploy(@Param("keyword") String keyword){
        return adminEmployMapper.selectEmploySearchCountAll(keyword);
    }

//    인턴 공고 - 상세정보
    public Optional<AdminInternNoticeDetailDTO> selectEmploy(Long id){
        return adminEmployMapper.selectEmployById(id);
    }

//    인턴 공고 - 신청자 정보
    public List<AdminInternNoticeDetailRequestDTO> selectRequest(@Param("criteria") AdminExperienceCriteria criteria,
                                                                 @Param("id") Long id){
        return adminEmployMapper.selectRequestUser(criteria, id);
    }

//    인턴 공고 - 신청자 정보 개수
    public int countRequest(Long id){
        return adminEmployMapper.countRequestUser(id);
    }
}
