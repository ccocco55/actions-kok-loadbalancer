package com.example.kok.service;

import com.example.kok.dto.AdminMainPageDTO;
import com.example.kok.dto.ChartDTO;

import java.util.List;

public interface AdminMainPageService {
//    AdminMainPageDAO
    public AdminMainPageDTO mainPage();

//    차트
    public List<ChartDTO> mainPageChart();
}
