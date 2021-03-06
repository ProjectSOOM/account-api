package com.soom.account_api.global.account.data.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.soom.account_api.global.account.exception.UnknownSchoolTypeException;

import java.util.Arrays;
import java.util.Locale;

public enum SchoolType {
    GWANGJU, BUSAN, DAEGU, DAEDEOK;

    @JsonCreator
    public static SchoolType of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name().toUpperCase(Locale.ROOT).equals(name.toUpperCase(Locale.ROOT)))
                .findAny()
                .orElseThrow(() -> new UnknownSchoolTypeException(name));
    }
}
