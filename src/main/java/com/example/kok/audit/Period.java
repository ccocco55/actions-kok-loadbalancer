package com.example.kok.audit;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@SuperBuilder
public abstract class Period {
    private String createdDateTime;
    private String updatedDateTime;
}
