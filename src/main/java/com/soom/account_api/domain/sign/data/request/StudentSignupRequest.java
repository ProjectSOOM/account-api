package com.soom.account_api.domain.sign.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soom.account_api.global.data.type.DepartmentType;

import java.time.LocalDate;

public record StudentSignupRequest(
        String emailToken,
        String password,
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate birth,
        Integer admissionYear,
        Integer schoolNumber,
        DepartmentType department
) {
}
