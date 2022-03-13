package com.soom.account_api.global.account.data.entity;

import com.soom.account_api.global.account.data.type.SchoolType;
import com.soom.account_api.global.account.data.type.TeacherType;
import com.soom.account_api.global.account.data.dto.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
public class TeacherEntity extends AccountEntity{
    private String code;
    private TeacherType teacher;

    public TeacherEntity(String email,
                         String encodedPassword,
                         String name,
                         LocalDate birth,
                         SchoolType schoolType,
                         String code,
                         TeacherType teacher) {
        super(email, encodedPassword, name, birth, schoolType);
        this.code = code;
        this.teacher = teacher;
    }

    @Override
    public TeacherDto toDto() {
        return new TeacherDto(
                new AccountAuthInfoDto(getEmail(), getEncodedPassword()),
                new AccountProfileInfoDto(getName(), getBirth(), getSchoolType()),
                new TeacherInfoDto(code, teacher)
        );
    }
}
