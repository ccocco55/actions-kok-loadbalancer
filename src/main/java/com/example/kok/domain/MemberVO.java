package com.example.kok.domain;

import com.example.kok.audit.Period;
import com.example.kok.enumeration.Provider;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="userId", callSuper = false)
@SuperBuilder
public class MemberVO extends Period{
    private Long userId;
    private Provider memberProvider;
    private String memberProfileUrl;
    private String memberInfo;
}
