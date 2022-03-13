package com.soom.account_api.global.account.repository;

import com.soom.account_api.global.account.data.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    boolean existsBySchoolNumber(Integer schoolNumber);
}
