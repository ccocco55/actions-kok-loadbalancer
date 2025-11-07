package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.*;
import com.example.kok.util.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public void joinMember(MemberVO memberVO);

    //    프로필 사진 삭제
    public void deleteProfile(Long id);

    //    보관함에 이력서 넣기
    public void putFileAtStorage(List<MultipartFile> file, Long memberId);

    //    이력서들 조회
    public List<FileDTO> findFilesByMemberId(Long memberId);

    public void deleteFileByFileId(Long fileId);

    //    모든 회원 조회
    public AdminMemberCriteriaDTO findUserMembers(int page, String keyword);

    //    회원별 모든 이력 조회
    public UserMemberDTO findMembersByMemberId(Long memberId);

    //    지원한 체험 공고 조회
    public List<RequestExperienceDTO> findRequestExperienceByMemberId(Long memberId);

    //    지원한 인턴 공고 조회
    public List<RequestInternDTO> findRequestInternByMemberId(Long memberId);

    //    작성 게시글 조회
    public List<PostDTO> findPostsByMemberId(Long memberId);

    //    결제내역 조회
    public List<PaymentDTO> findPaymentByMemberId(Long memberId);

    //    저장 체험 조회
    public List<ExperienceNoticeDTO> findExperienceNoticeByMemberId(Long memberId);

    //    저장 인턴 조회
    public List<InternNoticeDTO> findInternNoticeByMemberId(Long memberId);

    //    체험 지원 취소
    public void deleteRequestExperience(Long id);

    //    인턴 지원 취소
    public void deleteRequestIntern(Long id);

    //    프로필 수정
    public void updateProfile(Long id, UserMemberDTO dto, MultipartFile file);

    //    프로필 조회
    public UserProfileFileDTO getProfile(Long id);

    //    회원 정보 조회
    public Optional<UserMemberDTO> findProfileByMemberId(Long memberId);
}
