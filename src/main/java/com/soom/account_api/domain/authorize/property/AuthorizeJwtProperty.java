package com.soom.account_api.domain.authorize.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("authorize.jwt")
@RefreshScope
public class AuthorizeJwtProperty {
    private String secret;
    private Long expiredSeconds;
}
