package com.soom.account_api.global.security.service;

import com.soom.account_api.global.account.data.entity.AccountEntity;
import com.soom.account_api.global.account.template.AccountTemplate;
import com.soom.account_api.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountTemplate accountTemplate;

    @Override
    public UserDetailsImpl loadUserByUsername(String id) throws UsernameNotFoundException {
        return accountTemplate.doByIdTemplate(Long.parseLong(id), this::entityToUserDetails, false);
    }

    private UserDetailsImpl entityToUserDetails(AccountEntity entity) {
        return new UserDetailsImpl(
                entity.getEncodedPassword(),
                String.valueOf(entity.getId()),
                entity.getPermissions()
        );
    }
}
