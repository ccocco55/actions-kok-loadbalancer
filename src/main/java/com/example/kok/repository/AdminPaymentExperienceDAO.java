package com.example.kok.repository;

import com.example.kok.dto.AdminPaymentExperienceCountDTO;
import com.example.kok.dto.AdminPaymentExperienceDTO;
import com.example.kok.mapper.AdminPaymentExperienceMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminPaymentExperienceDAO {
    private final AdminPaymentExperienceMapper adminPaymentExperienceMapper;

//    체험 결제 목록
    public List<AdminPaymentExperienceDTO> paymentExperienceList (@Param("criteria") Criteria criteria,
                                                                 @Param("keyword") String keyword,
                                                                 @Param("category") String category) {
        return adminPaymentExperienceMapper.selectPaymentExperienceAllList(criteria, keyword, category);
    }

//    체험 결제 총 개수
    public int countListAll(@Param("keyword") String keyword,
                            @Param("category") String category){
        return adminPaymentExperienceMapper.countAllPaymentExperience(keyword, category);
    }

//    승인된 결제 총액
    public AdminPaymentExperienceCountDTO totalAccept(){
        return adminPaymentExperienceMapper.totalAcceptPaymentExperience();
    }

//    거절된 결제 총액
    public AdminPaymentExperienceCountDTO totalReject(){
        return adminPaymentExperienceMapper.totalRejectPaymentExperience();
    }
}
