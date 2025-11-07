package com.example.kok.service;

import com.example.kok.dto.FileDTO;
import com.example.kok.dto.PostFileDTO;
import com.example.kok.repository.CommunityPostFileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityPostFileServiceImpl implements CommunityPostFileService {
    private final CommunityPostFileDAO communityPostFileDAO;

    @Override
    public void saveFile(FileDTO fileDTO) {
        communityPostFileDAO.saveFile(fileDTO);
    }

    @Override
    public void savePostFile(PostFileDTO postFileDTO) {
        communityPostFileDAO.save(postFileDTO);
    }

    @Override
    public List<PostFileDTO> getFilesByPostId(Long postId) {
        return communityPostFileDAO.findAllByPostId(postId);
    }

    @Override
    public Optional<PostFileDTO> getFilePathByFileId(Long fileId) {
        return communityPostFileDAO.findPostFilePathByPostFileId(fileId);
    }

    @Override
    public Optional<PostFileDTO> getPostFile(Long id) {
        return communityPostFileDAO.findById(id);
    }

    @Override
    public void deleteFile(Long id) {
        communityPostFileDAO.deleteById(id);
    }
}
