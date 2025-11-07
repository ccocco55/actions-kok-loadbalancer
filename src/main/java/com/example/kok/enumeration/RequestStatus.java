package com.example.kok.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RequestStatus {
    AWAIT("await"), ACCEPT("accept"), REJECT("reject");

    private final String value;

    @JsonCreator
    RequestStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
