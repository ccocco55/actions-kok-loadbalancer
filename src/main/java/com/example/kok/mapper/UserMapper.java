package com.example.kok.mapper;

import com.example.kok.domain.UserVO;
import com.example.kok.dto.MemberDTO;
import com.example.kok.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
//    이메일로 전체 조회
    public Optional<UserDTO> selectByEmail(String userEmail);
//    id로 조회
    public UserDTO selectById(Long id);
//    이메일로 일반 회원 조회
    public Optional<UserDTO> selectByMemberEmail(String userEmail);
//    이메일로 기업 회원 조회
    public Optional<UserDTO> selectByCompanyEmail(String userEmail);
//    이메일로 관리자 조회
public Optional<UserDTO> selectByAdminEmail(String userEmail);
//    일반 회원 가입
    public void insertMember(UserDTO userDTO);
//    기업 회원 가입
    public void insertCompany(UserDTO userDTO);
//    관리자 회원 가입
    public void insertAdmin(UserDTO userDTO);
//    이메일로 회원 수 조회
    public int selectCountByEmail(String userEmail);
//    sns 이메일 조회
    public Optional<UserDTO> selectBySnsEmail(String snsEmail);
//    sns 이메일로 회원 가입
    public void insertSnsMember(UserDTO userDTO);
//    비밀번호 변경
    public void updatePassword(UserDTO userDTO);
//    전화번호로 이메일 조회
    public UserDTO selectEmailByPhone(String phone);
}
