package com.soom.account_api.global.account.data.dto;

import com.soom.account_api.global.account.data.type.DepartmentType;

public record StudentInfoDto(
        Integer admissionYear,
        Integer schoolNumber,
        DepartmentType department
) {
}
