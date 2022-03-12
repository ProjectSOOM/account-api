package com.soom.account_api.global.data.dto;

import com.soom.account_api.global.data.type.SchoolType;

import java.time.LocalDate;

public record AccountProfileInfoDto(
        String name,
        LocalDate birth,
        SchoolType school
) {
}
