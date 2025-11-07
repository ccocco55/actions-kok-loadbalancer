package com.example.kok.dto;

import com.example.kok.util.Criteria;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter @ToString
public class ConsoleExperienceNoticeCriteriaDTO {
    // 공고 리스트
    private List<ConsoleExperienceNoticeDTO> experienceLists;

    // 공고 개수
    private int totalCount;
    private int activeTotalCount;

    // 지원자 통계
    private int totalRequestCount;
    private int activeRequestCount;

    // 검색/페이징 조건
    private String keyword;
    private Criteria criteria;
}
