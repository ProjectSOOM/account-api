package com.soom.account_api.domain.sign.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("soom.login.jwt")
@RefreshScope
public class LoginTokenJwtProperty {
    private String secret;
    private Long accessTokenExpiredSeconds;
    private Long refreshTokenExpiredSeconds;
}
