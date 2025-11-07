package com.example.kok.mapper;

import com.example.kok.dto.AdminInternNoticeDetailDTO;
import com.example.kok.dto.AdminInternNoticeDetailRequestDTO;
import com.example.kok.dto.AdminInternNoticeListDTO;
import com.example.kok.util.AdminExperienceCriteria;
import com.example.kok.util.AdminExperienceListCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminEmployMapper {
//    인턴 공고 목록
    public List<AdminInternNoticeListDTO> selectEmployAll(@Param("criteria") AdminExperienceListCriteria criteria,
                                                          @Param("keyword") String keyword);

//    전체 개수
    public int selectEmploySearchCountAll(@Param("keyword") String keyword);

//    인턴 공고 상세정보(1)
    public Optional<AdminInternNoticeDetailDTO> selectEmployById(Long id);

//    인턴 공고 상세정보(2)
    public List<AdminInternNoticeDetailRequestDTO> selectRequestUser(@Param("criteria") AdminExperienceCriteria criteria,
                                                                     @Param("id") Long id);

//    인턴 공고 상세정보(2) 개수
    public int countRequestUser(Long id);
}
