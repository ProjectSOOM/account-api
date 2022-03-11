package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.sign.exception.AccountAuthorizeException;
import com.soom.account_api.global.entity.AccountEntity;
import com.soom.account_api.global.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountAuthorizeServiceImpl implements AccountAuthorizeService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long authorize(String email, String password) {
        final AccountEntity entity = accountRepository.findByEmail(email).orElseThrow(() -> new AccountAuthorizeException("이메일을 찾을 수 없습니다!"));
        if(!passwordEncoder.matches(password, entity.getEncodedPassword())) throw new AccountAuthorizeException("비밀번호가 잘못되었습니다!");
        return entity.getId();
    }
}
