package com.soom.account_api.global.error.property;

import com.soom.account_api.global.error.data.response.ErrorResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@ConfigurationProperties("soom.error")
@RefreshScope
@Getter @Setter
public class ErrorResponseProperty {
    private HashMap<String, ErrorResponse> properties;
    private String fatalMessage;
}
