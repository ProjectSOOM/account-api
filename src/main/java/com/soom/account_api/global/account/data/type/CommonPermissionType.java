package com.soom.account_api.global.account.data.type;

import com.soom.account_api.global.account.data.PermissionType;

public enum CommonPermissionType implements PermissionType {
    USER, MANAGER, OPERATOR, ADMINISTRATOR;

    @Override
    public String getPermission() {
        return name();
    }
}
