package com.example.kok.service;

public interface CommunityReportService {

//    게시글 신고
    public boolean reportPost(Long postId, Long memberId);
}
