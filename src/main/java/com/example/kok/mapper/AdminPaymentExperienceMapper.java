package com.example.kok.mapper;

import com.example.kok.dto.AdminPaymentExperienceCountDTO;
import com.example.kok.dto.AdminPaymentExperienceDTO;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminPaymentExperienceMapper {
//    체험 결제 목록
    public List<AdminPaymentExperienceDTO> selectPaymentExperienceAllList(@Param("criteria") Criteria criteria,
                                                                          @Param("keyword") String keyword,
                                                                          @Param("category") String category);

//    체험 결제 총 개수
    public int countAllPaymentExperience(@Param("keyword") String keyword,
                                         @Param("category") String category);

//    승인된 결제 총액
    public AdminPaymentExperienceCountDTO totalAcceptPaymentExperience();

//    거절된 결제 총액
    public AdminPaymentExperienceCountDTO totalRejectPaymentExperience();
}
