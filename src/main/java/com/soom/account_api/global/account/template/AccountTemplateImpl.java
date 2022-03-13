package com.soom.account_api.global.account.template;

import com.soom.account_api.domain.profile.exception.UnknownAccountException;
import com.soom.account_api.global.account.data.dto.AccountDto;
import com.soom.account_api.global.account.data.entity.AccountEntity;
import com.soom.account_api.global.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AccountTemplateImpl implements AccountTemplate {
    private final AccountRepository accountRepository;

    public  <T extends AccountEntity> AccountDto doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Consumer<T> callback) {
        return doByIdTemplate(accountId, repository, callback, true);
    }

    public <T extends AccountEntity> AccountDto doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Consumer<T> callback, boolean save) {
        return doByIdTemplate(accountId, repository, entity -> {
            callback.accept(entity);
            return entity.toDto();
        }, save);
    }

    @Override
    public <T extends AccountEntity, R> R doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Function<T, R> callback) {
        return doByIdTemplate(accountId, repository, callback, true);
    }

    @Override
    public <R> R doByIdTemplate(Long accountId, Function<AccountEntity, R> callback, boolean save) {
        return doByIdTemplate(accountId, accountRepository, callback, save);
    }

    @Override
    public <T extends AccountEntity, R> R doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Function<T, R> callback, boolean save) {
        final T entity = repository
                .findById(accountId)
                .orElseThrow(() -> new UnknownAccountException(accountId));
        R result = callback.apply(entity);
        if(save) accountRepository.save(entity);
        return result;
    }
}
