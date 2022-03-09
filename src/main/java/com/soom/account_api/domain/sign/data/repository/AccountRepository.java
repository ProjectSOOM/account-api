package com.soom.account_api.domain.sign.data.repository;

import com.soom.account_api.domain.sign.data.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
