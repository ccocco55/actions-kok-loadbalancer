package com.example.kok.service;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.dto.*;
import com.example.kok.enumeration.PostWarningStatus;
import com.example.kok.repository.AdminAdvertisementDAO;
import com.example.kok.repository.AdvertisementBackgroundFileDAO;
import com.example.kok.repository.CommunityPostDAO;
import com.example.kok.repository.UserDAO;
import com.example.kok.util.AdminAdvertisementCriteria;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminWarningMemberServiceImpl implements AdminWarningMemberService {
    private final CommunityPostDAO communityPostDAO;
    private final UserDAO userDAO;

    //    회원별 주의 삭제된 게시물 수
    @Override
    public int banPostCountByMemberId(Long memberId) {
        return communityPostDAO.banPostCount(memberId);
    }

    //    아이디로 게시물 상세 찾기
    @Override
    public PostDTO findById(Long id) {
        PostDTO postDTO = communityPostDAO.findById(id).orElse(null);

        Long postId = postDTO.getId();
        int banCount = communityPostDAO.banPostCount(postId);

        postDTO.setWraningCount(banCount);

        Long memberId = postDTO.getMemberId();
        UserDTO userDTO = userDAO.findById(memberId);
        postDTO.setUserEmail(userDTO.getUserEmail());

        return postDTO;
    }

    //    전체 주의 게시물
    @Override
    public AdimPostWarningDTO findWarningPosts(int page, List<Integer> ids, String keyword) {
        AdimPostWarningDTO adimPostWarningDTO = new AdimPostWarningDTO();

        PostWarningStatus status = null;
        if(keyword != null && !keyword.isBlank()) {
            status = PostWarningStatus.getStatusFromValue(keyword);
        }

        int count = communityPostDAO.countWarningPosts(ids, status);

        Criteria criteria = new Criteria(page, count);

        List<PostWarningDTO> posts = communityPostDAO.selectWarningPosts(criteria, ids, status);

        for  (PostWarningDTO postWarningDTO : posts) {
            postWarningDTO.setWraningCount(communityPostDAO.banPostCount(postWarningDTO.getMemberId()));
        }

        criteria.setCount(posts.size() + 1);
        criteria.setHasMore(posts.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

        criteria.setHasMore(posts.size() == criteria.getRowCount() + 1);
//        10개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            posts.remove(posts.size() - 1);
        }

        adimPostWarningDTO.setCriteria(criteria);
        adimPostWarningDTO.setPostWarningDTOList(posts);

        return adimPostWarningDTO;

    }

    //    강제 삭제
    @Override
    public void postSoftDelete(Long id) {
        communityPostDAO.updatePostWarningStatusForDelete(id);
    }

    //    상태 보류로 변경
    @Override
    public void postWarningStatusHold(Long id) {
        communityPostDAO.updatePostWarningStatusForHold(id);
    }
}
