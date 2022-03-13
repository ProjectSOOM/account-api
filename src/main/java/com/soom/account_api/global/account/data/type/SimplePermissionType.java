package com.soom.account_api.global.account.data.type;

import com.soom.account_api.global.account.data.PermissionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SimplePermissionType implements PermissionType {
    private final String permission;
}
