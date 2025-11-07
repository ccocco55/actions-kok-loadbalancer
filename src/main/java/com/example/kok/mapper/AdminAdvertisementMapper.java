package com.example.kok.mapper;

import com.example.kok.dto.AdminAdvertisementCountDTO;
import com.example.kok.dto.AdminAdvertisementDTO;
import com.example.kok.util.AdminAdvertisementCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminAdvertisementMapper {
//    광고 목록
    public List<AdminAdvertisementDTO> selectAllAdvertisementList(
                                    @Param("criteria") AdminAdvertisementCriteria criteria,
                                    @Param("keyword") String keyword,
                                    @Param("category") String category);

//    광고 개수
    public int countAllAdvertisement(@Param("keyword") String keyword, @Param("category") String category);

//    광고 상태 개수
    public AdminAdvertisementCountDTO countAdvertisementStatus();

//    광고 상세
    public Optional<AdminAdvertisementDTO> selectAdvertisementById(Long id);

//    광고 승인
    public void acceptAdvertisementById(Long id);

//    광고 거절
    public void rejectAdvertisementById(Long id);
}
