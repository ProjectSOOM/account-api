package com.soom.account_api.domain.profile.data.dto;

import com.soom.account_api.global.account.data.type.DepartmentType;

public record UpdateStudentProfileInfoDto (
        Integer admissionYear,
        Integer schoolNumber,
        DepartmentType department
) {
}
