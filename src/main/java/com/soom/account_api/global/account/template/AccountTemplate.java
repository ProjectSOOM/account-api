package com.soom.account_api.global.account.template;

import com.soom.account_api.global.account.data.dto.AccountDto;
import com.soom.account_api.global.account.data.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Consumer;
import java.util.function.Function;

public interface AccountTemplate {
    <T extends AccountEntity> AccountDto doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Consumer<T> callback);

    <T extends AccountEntity> AccountDto doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Consumer<T> callback, boolean save);

    <T extends AccountEntity, R> R doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Function<T, R> callback);

    <T extends AccountEntity, R> R doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Function<T, R> callback, boolean save);
}
