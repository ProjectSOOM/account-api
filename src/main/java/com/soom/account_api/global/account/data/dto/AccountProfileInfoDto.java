package com.soom.account_api.global.account.data.dto;

import com.soom.account_api.global.account.data.type.SchoolType;

import java.time.LocalDate;

public record AccountProfileInfoDto(
        String name,
        LocalDate birth,
        SchoolType school
) {
}
