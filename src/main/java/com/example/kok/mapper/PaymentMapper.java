package com.example.kok.mapper;

import com.example.kok.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
//    결제
    void insertPayment(PaymentDTO payment);

//    체험비 결제
    public void insertPaymentByMember(PaymentDTO payment);

//    체험 결제 상태 update
    public void updatePaymentByMember(Long id);
}
