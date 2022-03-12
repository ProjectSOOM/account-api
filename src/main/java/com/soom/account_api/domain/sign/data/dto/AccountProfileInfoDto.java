package com.soom.account_api.domain.sign.data.dto;

import com.soom.account_api.domain.sign.data.type.SchoolType;

import java.time.LocalDate;

public record AccountProfileInfoDto(
        String name,
        LocalDate birth,
        SchoolType school
) {
}
