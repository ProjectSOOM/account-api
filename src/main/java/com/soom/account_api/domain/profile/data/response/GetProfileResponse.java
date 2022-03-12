package com.soom.account_api.domain.profile.data.response;

import com.soom.account_api.global.data.dto.StudentDto;
import com.soom.account_api.global.data.dto.TeacherDto;
import com.soom.account_api.global.data.type.AccountType;

public record GetProfileResponse(
        AccountType type,
        StudentDto student,
        TeacherDto teacher
) {
}
