package com.soom.account_api.domain.sign.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Getter
@Setter
@Configuration
@ConfigurationProperties("school.email")
@RefreshScope
public class SchoolEmailProperty {
    private HashMap<String, String> regex;
}
