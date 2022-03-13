package com.soom.account_api.global.account.service;

import com.soom.account_api.global.account.data.PermissionType;
import com.soom.account_api.global.account.data.type.SimplePermissionType;
import com.soom.account_api.global.account.repository.AccountRepository;
import com.soom.account_api.global.account.template.AccountTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final AccountRepository accountRepository;
    private final AccountTemplate template;

    @Override
    public void addPermission(final Long accountId, final PermissionType... type) {
        template.doByIdTemplate(accountId, accountRepository, entity -> {entity.addPermission(type);});
    }

    @Override
    public void removePermission(final Long accountId, final PermissionType type) {
        template.doByIdTemplate(accountId, accountRepository, entity -> {entity.removePermission(type);});
    }

    @Override
    public List<PermissionType> getPermissionById(final Long accountId) {
        return template.doByIdTemplate(accountId, accountRepository, entity -> {
            return entity.getPermissions()
                    .stream()
                    .map((Function<String, PermissionType>) SimplePermissionType::new)
                    .toList();
        });
    }
}
