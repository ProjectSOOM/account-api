package com.soom.account_api.domain.profile.template;

import com.soom.account_api.global.account.data.dto.AccountDto;
import com.soom.account_api.global.account.data.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Consumer;

public interface ProfileTemplate {
    <T extends AccountEntity> AccountDto doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Consumer<T> callback);

    <T extends AccountEntity> AccountDto doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Consumer<T> callback, boolean save);
}
