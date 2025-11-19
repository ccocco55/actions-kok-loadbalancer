package com.example.kok.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PostWarningStatus {
    DELETE("delete"), HOLD("hold"), WAIT("wait");

    private final String value;
    private static final Map<String, PostWarningStatus> STATUS_MAP =
            Arrays.stream(PostWarningStatus.values()).collect(Collectors.toMap(PostWarningStatus::getValue, Function.identity()));
    PostWarningStatus(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return this.value;
    }
    @JsonCreator
    public static PostWarningStatus getStatusFromValue(String value) {
        return Optional.ofNullable(STATUS_MAP.get(value)).orElseThrow(IllegalArgumentException::new);
    }
}
