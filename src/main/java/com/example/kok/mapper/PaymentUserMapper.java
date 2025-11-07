package com.example.kok.mapper;

import com.example.kok.dto.PaymentDTO;
import com.example.kok.dto.PaymentUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentUserMapper {
//    결제
    void insertPaymentUser(PaymentUserDTO paymentUser);

//    체험비 결제 중간 테이블
    public void insertPaymentUserByMember(PaymentUserDTO paymentUser);
}
