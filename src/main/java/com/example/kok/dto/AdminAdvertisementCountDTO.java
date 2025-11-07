package com.example.kok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminAdvertisementCountDTO {
    private int acceptCount;
    private int awaitCount;
    private int rejectCount;
}
