package com.soom.account_api.global.account.service;

import com.soom.account_api.global.account.data.PermissionType;

import java.util.List;

public interface PermissionService {
    void addPermission(Long accountId, PermissionType... type);
    void removePermission(Long accountId, PermissionType type);
    List<PermissionType> getPermissionById(Long accountId);
}
