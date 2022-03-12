package com.soom.account_api.domain.sign.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soom.account_api.global.data.type.TeacherType;

import java.time.LocalDate;

public record TeacherSignupRequest(
        String emailToken,
        String password,
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate birth,
        String code,
        TeacherType teacher
) {
}
