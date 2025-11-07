package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="requestId")
public class ConsoleInternApplicantDTO {
    private long requestId; // 지원 요청 ID
    private RequestStatus requestInternStatus; // 지원 상태
    private LocalDate requestDatetime; // 지원 일시

    private long userId; // 지원자 ID
    private String userName; // 지원자 이름
    private String userEmail; // 이메일
    private String userPhone; // 핸드폰

    private long internNoticeId; // 공고 ID
    private String internNoticeTitle; // 공고 제목
    private long companyId; // 기업 ID

    private Long resumeFileId;
    private String filePath;
    private String fileName;

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // this 객체를 JSON 문자열로 변환
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return super.toString();
        }
    }
}
