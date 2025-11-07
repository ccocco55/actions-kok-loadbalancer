//package com.example.kok.mapper;
//
//import com.example.kok.dto.UserDTO;
//import com.example.kok.enumeration.Provider;
//import com.example.kok.enumeration.UserRole;
//import com.example.kok.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class AuthTests {
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void test(){
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserName("admin");
//        userDTO.setSnsEmail("admin");
//        userDTO.setUserRole(UserRole.MEMBER);
//        userDTO.setMemberProvider(Provider.GOOGLE);
//        userDTO.setUserPhone("010515151616");
//        userService.joinSnsUser(userDTO);
//    }
//}
