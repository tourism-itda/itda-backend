package com.tourism.itda.auth.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "password_reset_codes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordResetCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", nullable = false, length = 50)
    private String loginId;

    @Column(name = "code", nullable = false, length = 6)
    private String code;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "used", nullable = false)
    private boolean used = false;

    public static PasswordResetCode of(String loginId, String code) {
        PasswordResetCode entity = new PasswordResetCode();
        entity.loginId = loginId;
        entity.code = code;
        entity.expiresAt = LocalDateTime.now().plusMinutes(5);
        entity.used = false;
        return entity;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public void markUsed() {
        this.used = true;
    }
}
