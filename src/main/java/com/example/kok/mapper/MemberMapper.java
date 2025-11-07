package com.example.kok.mapper;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.*;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    public void insertMember(MemberVO memberVO);

//    프로필 사진 삭제
    public void deleteProfile(Long id);
    public void deleteProfileUrl(Long id);

//    프로필 사진 추가
    public void insertProfileFile(UserProfileFileDTO userProfileFileDTO);

//    회원 조회
    public List<UserMemberDTO> selectMembers(@Param("criteria") Criteria criteria,@Param("keyword") String keyword);

//    목록 개수 조회
    public int selectCount(String keyword);

//    아이디로 회원 조회
    public Optional<UserMemberDTO> selectMember(Long memberId);

//    아이디로 회원 프로필 기본 정보 조회
    public Optional<UserMemberDTO> selectProfileMember(Long memberId);

//    아이디로 지원한 체험 공고 조회
    public List<RequestExperienceDTO> selectExperienceByMemberId(Long memberId);

//    아이디로 지원한 인턴 공고 조회
    public List<RequestInternDTO> selectInternByMemberId(Long memberId);

//    아이디로 게시글 조회
    public List<PostDTO> selectPostsByMemberId(Long memberId);

//    아이디로 직군 조회
    public String selectJobCategoryByMemberId(Long memberId);
//    전화번호로 회원 sns이메일 조회
    public List<MemberDTO> selectLinkBYPhone(String userPhone);

//    id로 결제내역 조회
    public List<PaymentDTO> selectPaymentByMemberId(Long memberId);

//    id로 저장한 체험 공고 조회
    public List<ExperienceNoticeDTO> selectSavedExpByMemberId(Long memberId);

//    id로 저장한 인턴 공고 조회
    public List<InternNoticeDTO> selectSavedIntByMemberId(Long memberId);

//    지원 id로 체험 지원 취소
    public void updateExpReq(Long id);

//    지원 id로 인턴 지원 취소
    public void updateIntReq(Long id);

//    프로필 수정
    public void updateProfileInfo(Long memberId, String info);
    public void updateProfileName(Long id, String name);
    public void updateProfileCategory(Long id, String job);
    public void insertProfileJob(Long id, String job);
    public void updateProfileUrl(Long id, String s3Key);

}
