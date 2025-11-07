package com.example.kok.repository;

import com.example.kok.dto.FileDTO;
import com.example.kok.dto.PostFileDTO;
import com.example.kok.mapper.CommunityPostFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommunityPostFileDAO {
    private final CommunityPostFileMapper communityPostFileMapper;

//    파일 추가
    public void saveFile(FileDTO fileDTO) {
        communityPostFileMapper.insertFile(fileDTO);
    }

//    게시글 파일 추가
    public void save(PostFileDTO postFileDTO){
        communityPostFileMapper.insertPostFile(postFileDTO);
    }

//    조회
    public List<PostFileDTO> findAllByPostId(Long postId){
        return communityPostFileMapper.selectPostFilesByPostId(postId);
    }
    public Optional<PostFileDTO> findPostFilePathByPostFileId(Long postFileId){
        return communityPostFileMapper.selectPostFilePathByPostFileId(postFileId);
    }
    public Optional<PostFileDTO> findById(Long id){
        return communityPostFileMapper.selectPostFileById(id);
    }

//    삭제
    public void deleteById(Long fileId){
        System.out.println("🧪 DAO.deleteById 호출됨, fileId = " + fileId);
        communityPostFileMapper.deletePostFile(fileId);
        communityPostFileMapper.deleteFile(fileId);
    }

}
