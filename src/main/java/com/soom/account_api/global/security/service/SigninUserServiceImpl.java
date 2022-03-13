package com.soom.account_api.global.security.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SigninUserServiceImpl implements SigninUserService{
    @Override
    public Long getSigninUserId() {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return Long.parseLong(userDetails.getUsername());
    }
}
