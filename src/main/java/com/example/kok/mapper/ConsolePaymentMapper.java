package com.example.kok.mapper;

import com.example.kok.dto.*;
import com.example.kok.enumeration.Status;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsolePaymentMapper {

//    결제 등록
    public void insertPayment(ConsolePaymentDTO payment);

//    결제 삭제
    public void deleteByAdvertisementId(Long advertisementId);
    public void deletePaymentUserByAdvertisementId(Long advertisementId);

//    결제 목록
    public List<ConsolePaymentDTO> selectPaymentByCompany(@Param("companyId") Long companyId,
                                                             @Param("criteria") Criteria criteria);

//    결제 개수
    public int selectCountByCompany(@Param("companyId") Long companyId);

}
