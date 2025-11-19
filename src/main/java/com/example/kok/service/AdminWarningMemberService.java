package com.example.kok.service;

import com.example.kok.dto.AdimPostWarningDTO;
import com.example.kok.dto.PostDTO;

import java.util.List;

public interface AdminWarningMemberService {

//    회원별 주의 삭제된 게시물 수
    public int banPostCountByMemberId(Long memberId); 
    
//    아이디로 게시물 상세 찾기
    public PostDTO findById(Long id);

//    전체 주의 게시물  
    public AdimPostWarningDTO findWarningPosts(int page, List<Integer> ids, String keyword);

//    강제 삭제
    public void postSoftDelete(Long id);
    
//    상태 보류로 변경
    public void postWarningStatusHold(Long id);


}
