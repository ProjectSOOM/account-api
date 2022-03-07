package com.soom.account_api.domain.authorize.service;

import com.soom.account_api.domain.authorize.dto.AuthInfoDto;
import com.soom.account_api.domain.authorize.entity.AuthorizeInfoEntity;
import com.soom.account_api.domain.authorize.exception.UnknownCodeException;
import com.soom.account_api.domain.authorize.repository.AuthorizeInfoRepository;

import java.util.Random;

import static java.lang.Math.pow;

public class RedisAndSmtpAuthorizeService implements AuthorizeService{
    AuthorizeInfoRepository authorizeInfoRepository;

    @Override
    public String createAuthCode() {
        String authCode;
        while (true) {
            authCode = String.format("%6d", randomNumber(6));
            if (!authorizeInfoRepository.existsById(authCode)) return authCode;
        }
    }

    @Override
    public void sendAuthCode(final AuthInfoDto dto) {
        //TODO
    }

    @Override
    public void addAuthInfo(final AuthInfoDto dto) {
        authorizeInfoRepository.save(AuthorizeInfoEntity.of(dto));
    }

    @Override
    public void removeAuthInfo(final String code) {
        authorizeInfoRepository.deleteById(code);
    }

    @Override
    public String getEmailByCode(final String code) {
        return authorizeInfoRepository
                .findById(code)
                .orElseThrow(() -> new UnknownCodeException(code))
                .getEmail();
    }

    @Override
    public void getTokenByEmail(final String email) {
        //TODO
    }

    private Integer randomNumber(final Integer length) {
        return new Random().nextInt((int)pow(10, length + 1));
    }
}
