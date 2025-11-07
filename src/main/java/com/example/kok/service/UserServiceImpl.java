package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.dto.CompanyLicenseFileDTO;
import com.example.kok.dto.FileDTO;
import com.example.kok.dto.UserDTO;
import com.example.kok.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final MemberDAO memberDAO;
    private final UserDAO  userDAO;
    private final CompanyDAO companyDAO;
    private final PasswordEncoder passwordEncoder;
    private final MemberAlarmSettingDAO memberAlarmSettingDAO;
    private final S3Service s3Service;
    private final FileDAO fileDAO;
    private final CompanyLicenseFileDAO companyLicenseFileDAO;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinUser(UserDTO userDTO) {

        userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        userDAO.saveUser(userDTO);
        memberDAO.saveMember(MemberVO.builder().userId(userDTO.getId()).memberProvider(userDTO.getMemberProvider()).build());
        memberAlarmSettingDAO.save(userDTO.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinCompany(UserDTO userDTO, MultipartFile multipartFiles) throws IOException {
        String path = getPath();
        userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        userDAO.saveCompany(userDTO);
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyName(userDTO.getCompanyName());
        companyDTO.setUserId(userDTO.getId());
        companyDAO.saveCompany(companyDTO);
        FileDTO fileDTO = new FileDTO();
        String s3Key = s3Service.uploadFile(multipartFiles, getPath());
        fileDTO.setFileOriginName(multipartFiles.getOriginalFilename());
        fileDTO.setFileName(multipartFiles.getOriginalFilename());
        fileDTO.setFilePath(s3Key);
        fileDTO.setFileContentType(multipartFiles.getContentType());
        fileDTO.setFileSize(multipartFiles.getSize()+"");
        fileDAO.saveFile(fileDTO);
        CompanyLicenseFileDTO companyLicenseFileDTO = new CompanyLicenseFileDTO();
        companyLicenseFileDTO.setCompanyId(userDTO.getId());
        companyLicenseFileDTO.setFileId(fileDTO.getId());

        companyLicenseFileDAO.saveCompanyLicenseFile(companyLicenseFileDTO);


        s3Service.uploadFile(multipartFiles,path);
    }

    @Override
    public void joinAdmin(UserDTO userDTO) {
        userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        userDAO.saveAdmin(userDTO);
    }

    @Override
    public int searchUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinSnsUser(UserDTO userDTO) {
        log.info("userDTO={}", userDTO);
        userDAO.saveSnsUser(userDTO);
        memberDAO.saveMember(MemberVO.builder().userId(userDTO.getId()).memberProvider(userDTO.getMemberProvider()).memberProfileUrl(userDTO.getMemberProfileUrl()).build());
        memberAlarmSettingDAO.save(userDTO.getId());
    }

    @Override
    public UserDTO findById(Long id) {
        return userDAO.findById(id);
    }

    public String getPath() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return today.format(formatter);
    }

    public void deleteCache(String keyName){
        String name = keyName+"*";
        Set<String> keys = redisTemplate.keys(name);
        for (String key : keys) {
            redisTemplate.delete(key);
        }
    }
}
