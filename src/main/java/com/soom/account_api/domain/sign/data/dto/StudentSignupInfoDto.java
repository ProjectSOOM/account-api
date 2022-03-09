package com.soom.account_api.domain.sign.data.dto;

public record StudentSignupInfoDto (
        AccountAuthInfoDto authInfo,
        AccountProfileInfoDto profileInfo,
        StudentInfoDto studentInfo) {
}
