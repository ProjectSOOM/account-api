package com.soom.account_api.domain.sign.data.response;

public record LoginToken(
        String accessToken,
        String refreshToken
) {
}
