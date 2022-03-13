package com.soom.account_api.global.account.data.entity;

import com.soom.account_api.global.account.data.PermissionType;
import com.soom.account_api.global.account.data.type.SchoolType;
import com.soom.account_api.global.account.data.dto.AccountDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
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
    @Enumerated(value = EnumType.STRING)
    private SchoolType schoolType;
    //Account Meta Information
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> permissions = new ArrayList<>();

    public AccountEntity(final String email,
                         final String encodedPassword,
                         final String name,
                         final LocalDate birth,
                         final SchoolType schoolType) {
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.birth = birth;
        this.schoolType = schoolType;
    }

    public void addPermission(PermissionType... permission) {
        permissions.addAll(
                Arrays.stream(permission)
                        .map(PermissionType::getPermission)
                        .toList()
        );
    }

    public void removePermission(PermissionType permission) {
        permissions.remove(permission.getPermission());
    }

    public abstract <T extends AccountDto> T toDto();
}
