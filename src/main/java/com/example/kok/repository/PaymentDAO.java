package com.example.kok.repository;

import com.example.kok.dto.PaymentDTO;
import com.example.kok.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {
    private final PaymentMapper paymentMapper;

    public void insertPayment(PaymentDTO payment) {
        paymentMapper.insertPayment(payment);
    }

//    체험비 결제
    public void payRequestExp(PaymentDTO payment) {
        paymentMapper.insertPaymentByMember(payment);
    }

//    체험 결제 상태 업데이트
    public void canclePayment(Long id){
        paymentMapper.updatePaymentByMember(id);
    }
}
