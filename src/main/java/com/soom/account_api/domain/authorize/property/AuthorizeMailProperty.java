package com.soom.account_api.domain.authorize.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
@ConfigurationProperties("soom.authorize.mail")
@RefreshScope
public class AuthorizeMailProperty {
    private String title;
    private String templatePath;
}
