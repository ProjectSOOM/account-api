package com.soom.account_api.domain.profile.data.request;

import com.soom.account_api.global.data.type.DepartmentType;

public record UpdateStudentProfileRequest(
        Integer admissionYear,
        Integer schoolNumber,
        DepartmentType department
) {
}
