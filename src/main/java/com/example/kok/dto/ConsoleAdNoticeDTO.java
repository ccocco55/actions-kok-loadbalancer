package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class ConsoleAdNoticeDTO {
    private long id;
    private String advertisementMainText; // 광고 메인 텍스트
    private String advertisementSubText; // 광고 서브 텍스트
    private Status advertisementStatus; // 요청 상태
    private RequestStatus advertisementRequestStatus; // 승인 상태
    private String advertiseStartDatetime; // 광고 시작 날짜
    private String advertiseEndDatetime; // 광고 종료 날짜
    private long companyId; // 회사 아이디
    private String createdDateTime;
    private String updatedDateTime;
    private long paymentPrice; // 금액

    private List<FileDTO> uploadedFiles;
}
