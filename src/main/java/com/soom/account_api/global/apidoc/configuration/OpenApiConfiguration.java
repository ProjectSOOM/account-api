package com.soom.account_api.global.apidoc.configuration;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OperationCustomizer customizer() {
        return ((operation, handlerMethod) ->
                operation.addParametersItem(
                        new Parameter()
                                .in("header")
                                .required(false)
                                .name("Authorization")
                                .description("로그인 토큰을 담는 인증헤더입니다 (OpenAPI 명세에서는 헤더를 적용할 수 없습니다! PostMan 을 이용해주세요)")
                ));
    }
}
