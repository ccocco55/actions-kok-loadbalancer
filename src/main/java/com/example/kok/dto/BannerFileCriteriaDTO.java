package com.example.kok.dto;

import com.example.kok.util.Criteria;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BannerFileCriteriaDTO {
    private List<BannerFileDTO> bannerFileList;
    private Criteria criteria;
}
