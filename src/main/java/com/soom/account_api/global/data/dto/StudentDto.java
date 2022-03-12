package com.soom.account_api.global.data.dto;

import lombok.Getter;

@Getter
public class StudentDto extends AccountDto {
    private final StudentInfoDto student;

    public StudentDto(AccountAuthInfoDto auth, AccountProfileInfoDto profileInfoDto, StudentInfoDto student) {
        super(auth, profileInfoDto);
        this.student = student;
    }
}
