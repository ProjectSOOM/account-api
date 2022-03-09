package com.soom.account_api.domain.sign.data.dto;

public record TeacherSignupInfoDto(
        AccountAuthInfoDto authInfo,
        AccountProfileInfoDto profileInfo,
        TeacherInfoDto teacherInfo
) {
}
