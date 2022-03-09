package com.soom.account_api.domain.sign.data.dto;

import java.time.LocalDate;

public record AccountProfileInfoDto(
        String name,
        LocalDate birth
) {
}
