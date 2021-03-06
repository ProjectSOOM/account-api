package com.soom.account_api.global.account.repository;

import com.soom.account_api.global.account.data.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findById(Long id);
}
