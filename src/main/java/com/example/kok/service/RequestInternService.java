package com.example.kok.service;

import com.example.kok.dto.RequestInternDTO;

public interface RequestInternService {
//    지원서 내기
    public void applyForIntern(RequestInternDTO requestInternDTO);
//    지원 여부 판별
    public boolean isRequested(RequestInternDTO requestInternDTO);
}
