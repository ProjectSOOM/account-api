package com.soom.account_api.domain.profile.data.response;

import com.soom.account_api.global.account.data.dto.StudentDto;
import com.soom.account_api.global.account.data.dto.TeacherDto;
import com.soom.account_api.global.account.data.type.AccountType;

public record ProfileResponse(
        Long id,
        AccountType type,
        StudentDto student,
        TeacherDto teacher
) {
}
