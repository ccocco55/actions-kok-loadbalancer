package com.example.kok.mapper;

import com.example.kok.dto.AdminPaymentAdvertiseCountDTO;
import com.example.kok.dto.AdminPaymentAdvertiseDTO;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminPaymentAdvertiseMapper {
//    광고 결제 목록
    public List<AdminPaymentAdvertiseDTO> selectPaymentAdvertiseAllList(
                                                @Param("criteria") Criteria criteria,
                                                @Param("keyword") String keyword,
                                                @Param("category") String category);

//    광고 결제 총 개수
    public int countAllPaymentAdvertise(@Param("keyword") String keyword,
                                        @Param("category") String category);

//    승인된 결제 총액
    public AdminPaymentAdvertiseCountDTO countAcceptPaymentAdvertise();

//    승인된 결제 개수
    public AdminPaymentAdvertiseCountDTO totalAcceptPaymentAdvertise();
}
