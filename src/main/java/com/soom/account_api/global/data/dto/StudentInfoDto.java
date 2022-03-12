package com.soom.account_api.global.data.dto;

import com.soom.account_api.global.data.type.DepartmentType;

public record StudentInfoDto(
        Integer admissionYear,
        Integer schoolNumber,
        DepartmentType department
) {
}
