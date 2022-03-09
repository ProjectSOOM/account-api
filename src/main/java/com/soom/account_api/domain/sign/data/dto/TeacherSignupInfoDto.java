package com.soom.account_api.domain.sign.data.dto;

public record TeacherSignupInfoDto(
        AccountAuthInfoDto accountAuthInfoDto,
        AccountProfileInfoDto accountProfileInfoDto,
        TeacherInfoDto teacherInfoDto
) {
}
