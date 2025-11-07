package com.example.kok.repository;

import com.example.kok.domain.PostVO;
import com.example.kok.dto.PostDTO;
import com.example.kok.mapper.CommunityPostMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommunityPostDAO {
    private final CommunityPostMapper communityPostMapper;
//    목록
    public List<PostDTO> findAll(Criteria criteria){
        return communityPostMapper.selectCommunityPosts(criteria);
    }

//    전체 개수
    public int findCountAll() {
        return communityPostMapper.selectCommunityPostCount();
    }

//    조회
    public Optional<PostDTO> findById(Long id) {
        return communityPostMapper.selectCommunityPost(id);
    }

//    추가
    public void save(PostDTO postDTO) {
        communityPostMapper.insert(postDTO);
    }

//    삭제
    public void delete(Long id) {
        communityPostMapper.delete(id);
    }

//    수정
    public void update(PostVO postVO) {
        communityPostMapper.update(postVO);
    }

//    좋아요 수 증가
    public void increaseLikesCount(Long id) {
        communityPostMapper.increaseLikesCount(id);
    }

//    좋아요 수 감소
    public void decreaseLikesCount(Long id) {
        communityPostMapper.decreaseLikesCount(id);
    }

    //    회원 아이디로 게시물 조회
    public List<PostDTO> findPostById(Long id) {
        return communityPostMapper.selectPostById(id);
    };

//    회원 별 게시글 갯수
    public int findPostsCountByMemberId(Long memberId) {
        return communityPostMapper.selectPostsCountByMemberId(memberId);
    }
}
