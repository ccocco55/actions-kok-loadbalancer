package com.example.kok.repository;

import com.example.kok.domain.CompanyVO;
import com.example.kok.domain.UserVO;
import com.example.kok.dto.UserDTO;
import com.example.kok.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {
    private final UserMapper userMapper;
    public Optional<UserDTO> findByEmail(String userEmail) {
        return userMapper.selectByEmail(userEmail);
    }
//
    public UserDTO findById(Long id){
        return userMapper.selectById(id);
    }
//    일반 회원 가입
    public void saveUser(UserDTO userDTO) {
        userMapper.insertMember(userDTO);
    }
//    기업 회원 가입
    public void saveCompany(UserDTO userDTO) {
        userMapper.insertCompany(userDTO);
    }
//    관리자 회원 가입
    public void saveAdmin(UserDTO userDTO) {
        userMapper.insertAdmin(userDTO);
    }
//    이메일로 조회
    public int findUserByEmail(String email) {
        return userMapper.selectCountByEmail(email);
    }
//
    public Optional<UserDTO> findUserByMemberEmail(String email) { return userMapper.selectByMemberEmail(email); }
//
    public Optional<UserDTO> findUserByCompanyEmail(String email) { return userMapper.selectByCompanyEmail(email); }
//    sns 이메일로 조회
    public Optional<UserDTO> findUserBySnsEmail(String snsEmail) {
        return userMapper.selectBySnsEmail(snsEmail);
    }
//    sns 이메일로 회원가입
    public void saveSnsUser(UserDTO userDTO) {
        userMapper.insertSnsMember(userDTO);
    }
//    비밀번호 변경
    public void setPassword(UserDTO userDTO) {
        userMapper.updatePassword(userDTO);
    }
//    전화번호로 이메일번호 조회
    public UserDTO findEmailByPhone(String phone) {
        return userMapper.selectEmailByPhone(phone);
    }
}
