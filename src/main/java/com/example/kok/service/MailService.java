package com.example.kok.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    public void sendMail(String email,String name, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
        String code = createCode();

        Cookie cookie = new Cookie("code", code);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 10);
        response.addCookie(cookie);

        Cookie emailCookie = new Cookie("email", email);
        emailCookie.setPath("/");
        emailCookie.setMaxAge(60 * 30);
        response.addCookie(emailCookie);

        String receiver = email;
        String sender = "sinyoung3095@gmail.com";
        String title = "콕 인증메일";

        StringBuilder body = new StringBuilder();
        body.append("<html><body>");
        body.append("<p>"+name+"님 링크를 클릭해 인증해 주세요."+"</p>");
        body.append("<a href=\"http://localhost:10000/mail/find-password-ok?code=" + code + "\">인증 하러 가기</a>");
//        body.append("<img src=\"/images/member/kok_logo.png\">");

        body.append("</body></html>");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(body.toString(), true);

//        FileSystemResource fileSystemResource = new FileSystemResource(new File("", ""));
//        mimeMessageHelper.addInline("", fileSystemResource);


        javaMailSender.send(mimeMessage);
    }

    private String createCode(){
        String codes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "";
        Random random = new Random();

        for(int i=0; i<10; i++){
            code += codes.charAt(random.nextInt(codes.length()));
        }

        return code;
    }

    public void sendMailByNotice(String email,String name,String noticeTitle,String companyName) throws MessagingException {


        String receiver = email;
        String sender = "sinyoung3095@gmail.com";
        String title = "(콕)알림 : "+ noticeTitle;

        StringBuilder body = new StringBuilder();
        body.append("<html><body>");
        body.append("<p>"+name+"님 팔로우 하신 "+companyName+ "기업의 공지가 올라왔습니다."+"</p>");

        body.append("</body></html>");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(body.toString(), true);



        javaMailSender.send(mimeMessage);
    }
}















