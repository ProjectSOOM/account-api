package com.soom.account_api.domain.sign.data.entity;

import com.soom.account_api.domain.sign.data.type.DepartmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
public class StudentEntity extends AccountEntity{
    private Integer admissionYear;
    private Integer schoolNumber;
    private DepartmentType department;
}
