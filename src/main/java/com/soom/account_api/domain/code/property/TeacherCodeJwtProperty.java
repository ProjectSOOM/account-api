package com.soom.account_api.domain.code.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("teacher-code.jwt")
@RefreshScope
public class TeacherCodeJwtProperty {
    private String secret;
    private Long expiredDay;
    private String publicCode;
}
