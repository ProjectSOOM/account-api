package com.soom.account_api.global.entity;

import com.soom.account_api.domain.sign.data.type.DepartmentType;
import com.soom.account_api.domain.sign.data.type.SchoolType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
public class StudentEntity extends AccountEntity{
    private Integer admissionYear;
    private Integer schoolNumber;
    private DepartmentType department;

    public StudentEntity(String email,
                         String encodedPassword,
                         String name,
                         LocalDate birth,
                         SchoolType schoolType,
                         Integer admissionYear,
                         Integer schoolNumber,
                         DepartmentType department) {
        super(email, encodedPassword, name, birth, schoolType);
        this.admissionYear = admissionYear;
        this.schoolNumber = schoolNumber;
        this.department = department;
    }
}
