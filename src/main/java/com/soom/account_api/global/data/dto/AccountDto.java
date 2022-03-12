package com.soom.account_api.global.data.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountDto {
        private final AccountAuthInfoDto auth;
        private final AccountProfileInfoDto profileInfoDto;
}
