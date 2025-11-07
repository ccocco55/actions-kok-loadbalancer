package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="fileId")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestInternFileVO{
    private long fileId;
    private long requestInternId;
}
