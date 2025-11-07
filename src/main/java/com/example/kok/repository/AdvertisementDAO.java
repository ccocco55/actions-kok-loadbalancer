package com.example.kok.repository;

import com.example.kok.dto.AdvertisementDTO;
import com.example.kok.mapper.AdvertisementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdvertisementDAO {
    private final AdvertisementMapper advertisementMapper;

    public List<AdvertisementDTO> findAll() {
        return advertisementMapper.selectAllAdvertisement();
    }
}
