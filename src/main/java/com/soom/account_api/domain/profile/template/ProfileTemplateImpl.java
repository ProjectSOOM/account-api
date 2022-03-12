package com.soom.account_api.domain.profile.template;

import com.soom.account_api.domain.profile.exception.UnknownAccountException;
import com.soom.account_api.global.data.dto.AccountDto;
import com.soom.account_api.global.entity.AccountEntity;
import com.soom.account_api.global.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class ProfileTemplateImpl implements ProfileTemplate{
    private final AccountRepository accountRepository;

    public  <T extends AccountEntity> AccountDto doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Consumer<T> callback) {
        return doByIdTemplate(accountId, repository, callback, true);
    }

    public <T extends AccountEntity> AccountDto doByIdTemplate(Long accountId, JpaRepository<T, Long> repository, Consumer<T> callback, boolean save) {
        final T entity = repository
                .findById(accountId)
                .orElseThrow(() -> new UnknownAccountException(accountId));
        callback.accept(entity);
        if(save) accountRepository.save(entity);
        return entity.toDto();
    }
}
