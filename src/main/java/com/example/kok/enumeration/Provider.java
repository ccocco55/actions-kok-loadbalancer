package com.example.kok.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Provider {
    GOOGLE("google"), KAKAO("kakao"), NAVER("naver"), KOK("kok");

    private final String value;

    @JsonCreator
    Provider(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
