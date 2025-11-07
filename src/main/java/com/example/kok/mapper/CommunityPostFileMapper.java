package com.example.kok.mapper;

import com.example.kok.dto.FileDTO;
import com.example.kok.dto.PostFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommunityPostFileMapper {

//    파일 추가
    public void insertFile(FileDTO fileDTO);

//    게시글 파일 추가
    public void insertPostFile(PostFileDTO postFileDTO);

//    조회
    public List<PostFileDTO> selectPostFilesByPostId(@Param("postId") Long postId);
    public Optional<PostFileDTO> selectPostFilePathByPostFileId(@Param("fileId") Long fileId);
    public Optional<PostFileDTO> selectPostFileById(Long id);

//    삭제
    public void deletePostFile(@Param("fileId") Long fileId);
    public void deleteFile(@Param("fileId") Long fileId);

}
