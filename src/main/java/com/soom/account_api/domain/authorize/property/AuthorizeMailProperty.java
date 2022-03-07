package com.soom.account_api.domain.authorize.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
@ConfigurationProperties("authorize.mail")
public class AuthorizeMailProperty {
    private String title;
    private String templatePath;
}
