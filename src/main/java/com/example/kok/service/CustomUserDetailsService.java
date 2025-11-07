package com.example.kok.service;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.UserDTO;
import com.example.kok.repository.UserDAO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDAO userDAO;
    private final HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userEmail = null;
        String userRole = null;
        UserDTO userDTO = null;

        if(request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if("userEmail".equals(cookie.getName())){
                    userEmail = cookie.getValue();
                }
            }
        }
        if(userEmail == null){
            userDTO = userDAO.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("소유자를 찾을 수 없습니다."));
            log.info(userDTO.toString());
        }else {
            log.info("cutom:{}",username);
            userDTO = userDAO.findUserBySnsEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("sns 소유자를 찾을 수 없습니다."));
        }

        return new CustomUserDetails(userDTO);
    }
}
