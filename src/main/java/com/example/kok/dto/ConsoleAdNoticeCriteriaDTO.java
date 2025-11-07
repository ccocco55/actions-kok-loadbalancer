package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import com.example.kok.util.Criteria;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode
public class ConsoleAdNoticeCriteriaDTO {
    private List<ConsoleAdNoticeDTO> adLists;

    // 공고 개수
    private int totalCount;
    private int activeTotalCount;

    private long activeTotalPrice; // 총금액

    // 검색/페이징 조건
    private String keyword;
    private Criteria criteria;
}
