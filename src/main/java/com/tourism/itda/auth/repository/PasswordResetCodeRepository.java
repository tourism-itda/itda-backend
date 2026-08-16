package com.tourism.itda.auth.repository;

import com.tourism.itda.auth.entity.PasswordResetCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCode, Long> {

    Optional<PasswordResetCode> findTopByLoginIdAndUsedFalseOrderByExpiresAtDesc(String loginId);
}
