package com.soom.account_api.domain.sign.data.dto;

import com.soom.account_api.domain.sign.data.type.DepartmentType;

public record StudentInfoDto(
        Integer admissionYear,
        Integer schoolNumber,
        DepartmentType department
) {
}
