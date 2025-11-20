package com.example.kok.mapper;

import com.example.kok.domain.PostVO;
import com.example.kok.dto.PostDTO;
import com.example.kok.dto.PostWarningDTO;
import com.example.kok.enumeration.PostWarningStatus;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommunityPostMapper {
    //    목록
    public List<PostDTO> selectCommunityPosts(Criteria criteria);

    //    전체 게시물
    public List<PostWarningDTO> selectAllPosts();

    //    전체 개수
    public int selectCommunityPostCount();

    //    조회
    public Optional<PostDTO> selectCommunityPost(Long id);

    //    회원별 강제 삭제 개수
    public int selectBanPostCount(Long memberId);

    //    주의 게시물 전체 불러오기
    public List<PostWarningDTO> selectWarningPosts(
            @Param("criteria") Criteria criteria,
            @Param("ids") List<Integer> ids,
            @Param("keyword") PostWarningStatus keyword);

    // 개수
    public int countWarningPosts(@Param("ids") List<Integer> ids, @Param("keyword") PostWarningStatus keyword);

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

    //    게시물 강제 삭제
    public void updatePostWarningStatusForDelete(Long id);

    //    게시물 주의 보류
    public void updatePostWarningStatusForHold(Long id);
}
