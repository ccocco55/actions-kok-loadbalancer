package com.example.kok.mapper;

import com.example.kok.dto.AdvertisementDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvertisementMapper {

    public List<AdvertisementDTO> selectAllAdvertisement();
}
