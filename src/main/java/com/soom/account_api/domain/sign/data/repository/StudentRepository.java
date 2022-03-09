package com.soom.account_api.domain.sign.data.repository;

import com.soom.account_api.domain.sign.data.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
