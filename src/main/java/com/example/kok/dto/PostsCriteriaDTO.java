package com.example.kok.dto;

import com.example.kok.util.Criteria;
import lombok.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostsCriteriaDTO {
    private List<PostDTO> posts;
    private Criteria criteria;
}
