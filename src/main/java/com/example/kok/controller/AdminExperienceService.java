package com.example.kok.controller;

import com.example.kok.dto.AdminExperienceDetailDTO;

public interface AdminExperienceService {
    public AdminExperienceDetailDTO getDetailLists(int page, Long id);
}
