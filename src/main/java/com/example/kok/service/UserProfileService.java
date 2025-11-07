package com.example.kok.service;


import com.example.kok.dto.FileDTO;
import com.example.kok.dto.UserProfileFileDTO;

import java.util.Optional;


public interface UserProfileService {
    public String findProfileById(Long id);
}
