package com.soom.account_api.domain.sign.data.dto;

import com.soom.account_api.global.account.data.dto.AccountAuthInfoDto;
import com.soom.account_api.global.account.data.dto.AccountProfileInfoDto;
import com.soom.account_api.global.account.data.dto.TeacherInfoDto;

public record TeacherSignupInfoDto(
        AccountAuthInfoDto authInfo,
        AccountProfileInfoDto profileInfo,
        TeacherInfoDto teacherInfo
) {
}
