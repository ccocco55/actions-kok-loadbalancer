package com.example.kok.service;

import com.example.kok.dto.PostLikeDTO;
import com.example.kok.repository.CommunityLikeDAO;
import com.example.kok.repository.CommunityPostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityLikeServiceImpl implements CommunityLikeService {
    private final CommunityLikeDAO communityLikeDAO;
    private final CommunityPostDAO communityPostDAO;

    @Override
    @CacheEvict(value = "posts", key = "'post_' + #postLikeDTO.postId")
    public void postLike(PostLikeDTO postLikeDTO) {
        communityLikeDAO.savePostLike(postLikeDTO);
        communityPostDAO.increaseLikesCount(postLikeDTO.getPostId());
    }

    @Override
    @CacheEvict(value = "posts", key = "'post_' + #postId")
    public void removePostLike(Long postId, Long memberId) {
        communityLikeDAO.deletePostLike(postId, memberId);
        communityPostDAO.decreaseLikesCount(postId);
    }

    @Override
    public int getPostLikeCount(Long postId) {
        return communityLikeDAO.getPostLikeCount(postId);
    }

    @Override
    public boolean checkedPostLike(Long postId, Long memberId) {
        return communityLikeDAO.isexistLike(postId, memberId);
    }
}
