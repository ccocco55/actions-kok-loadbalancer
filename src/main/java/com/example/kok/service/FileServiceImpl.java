package com.example.kok.service;

import com.example.kok.dto.FileDTO;
import com.example.kok.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileDAO fileDAO;
    @Override
    public Optional<FileDTO> findFileByCompanyId(Long userId) {
        return fileDAO.findFileByCompanyId(userId);
    }
}
