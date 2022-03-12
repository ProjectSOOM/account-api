package com.soom.account_api.global.data.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Locale;

@Getter
@RequiredArgsConstructor
public enum DepartmentType {
    GSM_COMMON("1학년 공통"), GSM_SW_DEVELOP("SW 개발과"), GSM_SMART_IOT("스마트 IoT과");

    private final String displayName;

    @JsonCreator
    public static DepartmentType from(String str) {
        try {
            return DepartmentType.valueOf(str.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            return Arrays.stream(values())
                    .filter(type -> type.getDisplayName().equals(str))
                    .findFirst().orElseThrow(() -> e);
        }
    }
}
