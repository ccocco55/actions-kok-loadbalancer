package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import com.example.kok.util.Criteria;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class AdimPostWarningDTO {
    private List<PostWarningDTO> postWarningDTOList;
    private Criteria criteria;

}
