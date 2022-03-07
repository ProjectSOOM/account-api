package com.soom.account_api.domain.authorize.repository;

import com.soom.account_api.domain.authorize.data.entity.AuthorizeInfoEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorizeInfoRepository extends CrudRepository<AuthorizeInfoEntity, String> {

}
