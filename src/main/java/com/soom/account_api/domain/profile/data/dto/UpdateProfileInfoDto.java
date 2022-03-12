package com.soom.account_api.domain.profile.data.dto;

import java.time.LocalDate;

public record UpdateProfileInfoDto (
        String name,
        LocalDate birth
) {
}
