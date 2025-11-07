package com.example.kok.repository;

import com.example.kok.dto.AdminAdvertisementCountDTO;
import com.example.kok.dto.AdminAdvertisementDTO;
import com.example.kok.mapper.AdminAdvertisementMapper;
import com.example.kok.util.AdminAdvertisementCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdminAdvertisementDAO {
    private final AdminAdvertisementMapper adminAdvertisementMapper;

//    광고 목록
    public List<AdminAdvertisementDTO> getAdvertisementList(
            @Param("criteria") AdminAdvertisementCriteria criteria, @Param("keyword") String keyword, @Param("category") String category) {
        return adminAdvertisementMapper.selectAllAdvertisementList(criteria, keyword, category);
    }

//    광고 개수
    public int countAll(@Param("keyword") String keyword, @Param("category") String category) {
        return adminAdvertisementMapper.countAllAdvertisement(keyword, category);
    }

//    광고 상태 개수
    public AdminAdvertisementCountDTO countStatus() {
        return adminAdvertisementMapper.countAdvertisementStatus();
    }

//    광고 상세
    public Optional<AdminAdvertisementDTO> selectAdvertisement(Long id) {
        return adminAdvertisementMapper.selectAdvertisementById(id);
    }

//    광고 승인
    public void acceptAdvertisement(Long id){
        adminAdvertisementMapper.acceptAdvertisementById(id);
    }

//    광고 거절
    public void rejectAdvertisement(Long id){
        adminAdvertisementMapper.rejectAdvertisementById(id);
    }
}
