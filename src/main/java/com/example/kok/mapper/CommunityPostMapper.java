package com.example.kok.mapper;

import com.example.kok.domain.PostVO;
import com.example.kok.dto.PostDTO;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommunityPostMapper {
//    목록
    public List<PostDTO> selectCommunityPosts(Criteria criteria);

//    전체 개수
    public int selectCommunityPostCount();

//    조회
    public Optional<PostDTO> selectCommunityPost(Long id);

//    추가
    public void insert(PostDTO postDTO);

//    삭제
    public void delete(Long id);

//    수정
    public void update(PostVO postVO);

//    좋아요 수 증가
    public void increaseLikesCount(Long id);

//    좋아요 수 감수
    public void decreaseLikesCount(Long id);

//    회원 아이디로 게시물 조회
    public List<PostDTO> selectPostById(Long id);

//    회원 별 게시글 갯수
    public int selectPostsCountByMemberId(@Param("memberId") Long memberId);
}
