package com.example.kok.repository;

import com.example.kok.dto.FileDTO;
import com.example.kok.dto.MemberStorageFileDTO;
import com.example.kok.mapper.MemberStorageFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberStorageFileDAO {
    private final MemberStorageFileMapper memberStorageFileMapper;

//    보관함에 파일 넣기
    public void saveStorageFile(MemberStorageFileDTO memberStorageFileDTO) {
        memberStorageFileMapper.insertStorageFile(memberStorageFileDTO);
    }

//    멤버 id로 보관함 파일들 조회
    public List<FileDTO> findFilesByMemberId(Long memberId) {
        return memberStorageFileMapper.selectStorageFilesByMemberId(memberId);
    }

//    파일 id로 보관함 파일 삭제
    public void deleteFileByFileId(Long fileId) {
        memberStorageFileMapper.deleteStorageFileByFileId(fileId);
    }
}
