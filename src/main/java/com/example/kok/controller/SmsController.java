package com.example.kok.controller;


import com.example.kok.dto.UserDTO;
import com.example.kok.repository.UserDAO;
import com.example.kok.service.SmsService;
import com.example.kok.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sms/**")
@Slf4j
public class SmsController {
    private final SmsService smsService;
    private final UserDAO  userDAO;
    private final UserService userService;
    private final HttpServletResponse response;

    @PostMapping("/send")
    public ResponseEntity<?> sendSms(@RequestBody Map<String, String> request) {
        String phone = request.get("userPhone");
        Map<String, Object> body = new HashMap<>();

        body.put("success", true);
        body.put("code", smsService.send(phone));
        return ResponseEntity.ok(body);
    }

    @PostMapping("/find-email")
    public ResponseEntity<?> findEmail(@RequestBody Map<String, String> request) {
        String phone = request.get("userPhone");
        Map<String, Object> body = new HashMap<>();

        UserDTO user = userDAO.findEmailByPhone(phone);
        log.info(user.toString());
        if (user != null) {
            log.info("이메일 찾기 성공");
            body.put("success", true);
            body.put("email", user.getUserEmail());

            Cookie cookie = new Cookie("email", user.getUserEmail());
            cookie.setMaxAge(60 * 60 * 24 * 30);
            cookie.setPath("/");
            cookie.setHttpOnly(true);

            response.addCookie(cookie);

        } else {
            body.put("success", false);
            body.put("message", "가입된 계정을 찾을 수 없습니다.");
        }

        return ResponseEntity.ok(body);
    }

}
