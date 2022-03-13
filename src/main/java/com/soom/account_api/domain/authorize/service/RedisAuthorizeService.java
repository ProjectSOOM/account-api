package com.soom.account_api.domain.authorize.service;

import com.soom.account_api.domain.authorize.data.dto.AuthInfoDto;
import com.soom.account_api.domain.authorize.data.entity.AuthorizeInfoEntity;
import com.soom.account_api.domain.authorize.exception.UnknownCodeException;
import com.soom.account_api.domain.authorize.repository.AuthorizeInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.Math.pow;

@Service
@RequiredArgsConstructor
public class RedisAuthorizeService implements AuthorizeService{
    private final AuthorizeMailSenderService authorizeMailSenderService;
    private final AuthorizeTokenService authorizeTokenService;
    private final AuthorizeInfoRepository authorizeInfoRepository;

    @Override
    public String createAuthCode() {
        String authCode;
        do { authCode = generateAuthCode(); }
        while (checkAuthCodePolicy(authCode));
        return authCode;
    }

    private boolean checkAuthCodePolicy(String authCode) {
        return authorizeInfoRepository.existsById(authCode);
    }
    private String generateAuthCode() {
        return String.format("%06d", randomNumber(6));
    }
    private Integer randomNumber(final Integer length) {
        return new Random().nextInt((int)pow(10, length));
    }

    @Override
    public void sendAuthCode(final AuthInfoDto dto) {
        authorizeMailSenderService.send(dto);
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
    public String getEmailByToken(final String token) {
        return authorizeTokenService.email(token);
    }

    @Override
    public String getTokenByEmail(final String email) {
        return authorizeTokenService.token(email);
    }
}
