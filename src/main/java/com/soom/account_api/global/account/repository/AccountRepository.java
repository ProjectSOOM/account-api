package com.soom.account_api.global.account.repository;

import com.soom.account_api.global.account.data.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByEmail(String email);
    Optional<AccountEntity> findByEmail(String email);
}
