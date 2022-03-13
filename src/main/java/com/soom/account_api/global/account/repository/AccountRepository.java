package com.soom.account_api.global.account.repository;

import com.soom.account_api.global.account.data.entity.AccountEntity;
import com.soom.account_api.global.account.exception.UnknownAccountException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByEmail(String email);
    Optional<AccountEntity> findByEmail(String email);
    Optional<AccountEntity> findById(Long id);
    default AccountEntity findOrThrowById(Long id) {
        return findById(id)
                .orElseThrow(() -> new UnknownAccountException(id));
    }
}
