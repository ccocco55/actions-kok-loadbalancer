package com.example.kok.repository;

import com.example.kok.dto.PaymentDTO;
import com.example.kok.dto.PaymentUserDTO;
import com.example.kok.mapper.PaymentMapper;
import com.example.kok.mapper.PaymentUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentUserDAO {
    private final PaymentUserMapper paymentUserMapper;

    public void insertPaymentUser(PaymentUserDTO paymentUser) {
        paymentUserMapper.insertPaymentUser(paymentUser);
    }

    public void payRequestUserExp(PaymentUserDTO paymentUser) {
        paymentUserMapper.insertPaymentUserByMember(paymentUser);
    }
}
