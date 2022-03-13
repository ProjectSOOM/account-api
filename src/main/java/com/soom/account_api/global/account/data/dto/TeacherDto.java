package com.soom.account_api.global.account.data.dto;

import lombok.Getter;

@Getter
public class TeacherDto extends AccountDto {
    private final TeacherInfoDto teacher;

    public TeacherDto(AccountAuthInfoDto auth, AccountProfileInfoDto profileInfoDto, TeacherInfoDto teacher) {
        super(auth, profileInfoDto);
        this.teacher = teacher;
    }
}
