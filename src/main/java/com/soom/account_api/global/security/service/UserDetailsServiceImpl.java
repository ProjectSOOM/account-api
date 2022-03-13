package com.soom.account_api.global.security.service;

import com.soom.account_api.global.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
