package com.example.kok.dto;

import com.example.kok.util.Criteria;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentsCriteriaDTO {
    private List<CommentDTO> comments;
    private Criteria criteria;
}
