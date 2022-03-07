package com.soom.account_api.domain.authorize.data.entity;

import com.soom.account_api.domain.authorize.data.dto.AuthInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "authorize-info", timeToLive = 300L)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeInfoEntity {
    @Id
    private String code;
    private String email;

    public static AuthorizeInfoEntity of(AuthInfoDto dto) {
        return new AuthorizeInfoEntity(dto.code(), dto.email());
    }
}
