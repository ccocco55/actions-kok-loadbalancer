package com.example.kok.dto;

import com.example.kok.util.Criteria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
public class AdminMemberCriteriaDTO {
    private List<UserMemberDTO> userMemberDTOList ;
    private Criteria criteria;
    private int total;

}
