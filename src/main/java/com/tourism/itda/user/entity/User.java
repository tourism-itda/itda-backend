package com.tourism.itda.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="users")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="login_id", length=50, nullable = false)
    private String loginId;

    @Column(name="password", length=255, nullable = false)
    private String password; //여기엔 암호화된 값만 저장 (Service에서 BCrypt로 인코딩 후 세팅)

    @Column(name="name", length=50, nullable = false)
    private String name;

    @Column(name="nickname", length=50, nullable = false)
    private String nickname;

    @Column(name="email", length=255)
    private String email;

    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column(name="profile_url", length=500)
    private String profileUrl;

    @Column(name="provider", length= 20)
    private String provider; // 카카오 로그인 시에만 값 존재 (예:"KAKAO", 일반 가입은 null)

    @Column(name="provider_uid", length=100)
    private String providerUid;

    @Column(name = "agreed_to_terms", nullable = false)
    private Boolean agreedToTerms;

    @Column(name = "agreed_at")
    private LocalDateTime agreedAt;

    @Column(name = "dark_mode")
    private Boolean darkMode = false; // 필드 기본값 설정 (DB에도 DEFAULT 걸어두는 걸 추천)

    @Column(name = "language", length = 10)
    private String language="ko";

    @Column(name = "notification_enabled")
    private Boolean notificationEnabled = true;

    @Column(name = "created_at", updatable = false)
    // updatable = false: 최초 저장 후엔 이 컬럼을 UPDATE 쿼리에서 제외
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist // 이 엔티티가 처음 저장(INSERT)되기 직전에 자동 호출되는 JPA 콜백
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 실제 객체 생성은 이 생성자로 강제 (회원가입 시 필요한 값만 받도록)
    public User(String loginId, String password, String name, String nickname,
                String email, LocalDate birthDate, Boolean agreedToTerms) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.birthDate = birthDate;
        this.agreedToTerms = agreedToTerms;
        this.agreedAt = agreedToTerms ? LocalDateTime.now() : null;
    }

    public static User ofKakao(String providerUid, String nickname, String email, String profileUrl){
        User user = new User();
        user.loginId = "KAKAO_" +providerUid;
        user.password = "";
        user.name = nickname;
        user.nickname = nickname;
        user.email = email;
        user.profileUrl = profileUrl;
        user.provider = "KAKAO";
        user.providerUid = providerUid;
        user.agreedToTerms = true;
        user.agreedAt = LocalDateTime.now();
        return user;
    }

    // 상태 변경이 필요한 필드만 의도가 드러나는 메서드로 노출
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public void changeDarkMode(Boolean darkMode) {
        this.darkMode = darkMode;
    }

    public void changeLanguage(String language) {
        this.language = language;
    }

    public void changeNotificationEnabled(Boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
