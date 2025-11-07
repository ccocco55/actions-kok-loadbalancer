package com.example.kok.service;

import com.example.kok.domain.PostVO;
import com.example.kok.dto.PostDTO;
import com.example.kok.dto.PostsCriteriaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityPostService {
    //    게시글 전체 조회
    public PostsCriteriaDTO getList(int page, Long memberId);
    //    조회
    public PostDTO getPost(Long id, Long memberId);
    public void setPreSignedUrl(PostDTO postDTO);
    public int getPostsCountByMemberId(Long memberId);
    //    추가
    public void write(PostDTO postDTO, List<MultipartFile> multipartFiles);
    //    삭제
    public void delete(Long id);
    //    수정
    public PostDTO update(PostDTO postDTO, Long[] deleteFiles, List<MultipartFile> files);

    default PostVO toPostVO(PostDTO postDTO){
        return PostVO.builder()
                .id(postDTO.getId())
                .postContent(postDTO.getPostContent())
                .postStatus(postDTO.getPostStatus())
                .memberId(postDTO.getMemberId())
                .build();
    }
}
