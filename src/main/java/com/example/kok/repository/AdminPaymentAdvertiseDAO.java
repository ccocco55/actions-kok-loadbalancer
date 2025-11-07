package com.example.kok.repository;

import com.example.kok.dto.AdminPaymentAdvertiseCountDTO;
import com.example.kok.dto.AdminPaymentAdvertiseDTO;
import com.example.kok.mapper.AdminPaymentAdvertiseMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminPaymentAdvertiseDAO {
    private final AdminPaymentAdvertiseMapper adminPaymentAdvertiseMapper;

//    광고 결제 목록
    public List<AdminPaymentAdvertiseDTO> getPaymentAdvertiseList(
                                                @Param("criteria") Criteria criteria,
                                                @Param("keyword") String keyword,
                                                @Param("category") String category) {
        return adminPaymentAdvertiseMapper.selectPaymentAdvertiseAllList(criteria, keyword, category);
    }

//    광고 결제 총 개수
    public int countListAll(@Param("keyword") String keyword,
                            @Param("category") String category){
        return adminPaymentAdvertiseMapper.countAllPaymentAdvertise(keyword, category);
    }

//    승인된 결제 총액
    public AdminPaymentAdvertiseCountDTO countAccept(){
        return adminPaymentAdvertiseMapper.countAcceptPaymentAdvertise();
    }

//    승인된 결제 개수
    public AdminPaymentAdvertiseCountDTO totalAccept(){
        return adminPaymentAdvertiseMapper.totalAcceptPaymentAdvertise();
    }
}
