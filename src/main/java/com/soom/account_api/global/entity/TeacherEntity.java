package com.soom.account_api.global.entity;

import com.soom.account_api.domain.sign.data.type.TeacherType;
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
                         String code,
                         TeacherType teacher) {
        super(email, encodedPassword, name, birth);
        this.code = code;
        this.teacher = teacher;
    }
}
