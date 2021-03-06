package com.soom.account_api.domain.sign.property;


import com.soom.account_api.global.account.data.type.SchoolType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Getter
@Setter
@Configuration
@ConfigurationProperties("soom.school.email")
@RefreshScope
public class SchoolEmailProperty {
    private HashMap<SchoolType, String> regex;
}
