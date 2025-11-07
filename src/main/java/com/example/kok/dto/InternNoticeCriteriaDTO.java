package com.example.kok.dto;

import com.example.kok.util.Criteria;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
//@EqualsAndHashCode(of = "id")
public class InternNoticeCriteriaDTO {
    private List<InternNoticeDTO> interns;
    private Criteria criteria;
}
