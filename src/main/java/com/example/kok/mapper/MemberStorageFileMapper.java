package com.example.kok.mapper;

import com.example.kok.dto.FileDTO;
import com.example.kok.dto.MemberStorageFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberStorageFileMapper {
//    보관함 파일 넣기
    public void insertStorageFile(MemberStorageFileDTO memberStorageFileDTO);

//    멤버 id로 보관함 파일 조회
    public List<FileDTO> selectStorageFilesByMemberId(Long memberId);

//    파일 id로 삭제
    public void deleteStorageFileByFileId(Long fileId);
}
