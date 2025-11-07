package com.example.kok.service;

import com.solapi.sdk.SolapiClient;
import com.solapi.sdk.message.exception.SolapiMessageNotReceivedException;
import com.solapi.sdk.message.model.Message;
import com.solapi.sdk.message.service.DefaultMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsService {
    //    코드 생성
    private String createCode(){
        String codes = "0123456789";
        String code = "";
        Random random = new Random();

        for(int i=0; i<6; i++){
            code += codes.charAt(random.nextInt(codes.length()));
        }

        return code;
    }

    //    문자 발송
    public String send(String phone) {
        String code = createCode();

     DefaultMessageService messageService =  SolapiClient.INSTANCE.createInstance("NCSYINPFEQ3EZM5G", "YP9MKYSX67TLIUDUBWJ3VAIPNY5S2RVC");
    // Message 패키지가 중복될 경우 com.solapi.sdk.message.model.Message로 치환하여 주세요
    Message message = new Message();
    message.setFrom("01051133095");
    message.setTo(phone);
    message.setText(code);

    try {
      // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
      messageService.send(message);
    } catch (SolapiMessageNotReceivedException exception) {
      // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
      System.out.println(exception.getFailedMessageList());
      System.out.println(exception.getMessage());
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }

            return code;
        }
}
