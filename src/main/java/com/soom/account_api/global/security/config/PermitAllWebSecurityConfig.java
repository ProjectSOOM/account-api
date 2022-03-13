package com.soom.account_api.global.security.config;

import com.soom.account_api.global.account.data.type.CommonPermissionType;
import com.soom.account_api.global.error.filter.ExceptionHandlerFilter;
import com.soom.account_api.global.security.filter.JwtAuthenticateFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class PermitAllWebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticateFilter jwtAuthenticateFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http //정책 초기화
                .cors().disable()
                .csrf().disable()
                //Stateless 적용
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //RequestMapping 초기화
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/api/v2/account/profile/**").hasRole(CommonPermissionType.USER.getPermission())
                .anyRequest().permitAll();
        http.addFilterBefore(jwtAuthenticateFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, JwtAuthenticateFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
