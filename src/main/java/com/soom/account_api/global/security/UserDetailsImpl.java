package com.soom.account_api.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final String password;
    private final String id;
    private final List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                //NOTE https://ruzun88.github.io/spring/security/403-forbidden.html#config-설정-돌아보기 참고
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }

    @Override public String getPassword() {return password;}
    @Override public String getUsername() {return id;}

    @Override public boolean isAccountNonExpired() {return true;}
    @Override public boolean isAccountNonLocked() {return true;}
    @Override public boolean isCredentialsNonExpired() {return true;}
    @Override public boolean isEnabled() {return true;}
}
