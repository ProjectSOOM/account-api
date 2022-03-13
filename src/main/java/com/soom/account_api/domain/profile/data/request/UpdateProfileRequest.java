package com.soom.account_api.domain.profile.data.request;

import java.time.LocalDate;

public record UpdateProfileRequest(
        String name,
        LocalDate birth
) {
}
