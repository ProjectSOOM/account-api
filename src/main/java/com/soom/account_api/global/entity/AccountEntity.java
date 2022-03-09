package com.soom.account_api.global.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
@NoArgsConstructor
public abstract class AccountEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Account Authorize Information
    private String email;
    private String encodedPassword;
    //Account Profile Information
    private String name;
    private LocalDate birth;

    public AccountEntity(String email, String encodedPassword, String name, LocalDate birth) {
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.birth = birth;
    }
}
