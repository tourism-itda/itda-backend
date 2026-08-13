package com.tourism.itda.auth.service;

import com.tourism.itda.auth.dto.*;
import com.tourism.itda.auth.entity.PasswordResetCode;
import com.tourism.itda.auth.exception.DuplicateLoginIdException;
import com.tourism.itda.auth.exception.DuplicateEmailException;
import com.tourism.itda.auth.exception.DuplicateNicknameException;
import com.tourism.itda.auth.exception.LoginFailedException;
import com.tourism.itda.auth.repository.PasswordResetCodeRepository;
import com.tourism.itda.global.client.KakaoAuthClient;
import com.tourism.itda.global.email.EmailService;
import com.tourism.itda.global.jwt.JwtProvider;
import com.tourism.itda.user.entity.User;
import com.tourism.itda.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDate;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final KakaoAuthClient kakaoAuthClient;
    private final PasswordResetCodeRepository resetCodeRepository;
    private final EmailService emailService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtProvider jwtProvider, KakaoAuthClient kakaoAuthClient,
                       PasswordResetCodeRepository resetCodeRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.kakaoAuthClient = kakaoAuthClient;
        this.resetCodeRepository = resetCodeRepository;
        this.emailService = emailService;
    }

    public UserResponse signup(String loginId, String password, String name, String nickname, String email,
                               LocalDate birthDate, Boolean agreedToTerms) {

        if (userRepository.existsByLoginId(loginId)) {
            throw new DuplicateLoginIdException(loginId);
        }

        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateNicknameException(nickname);
        }

        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(loginId, encodedPassword, name, nickname, email, birthDate, agreedToTerms);

        User saveduser = userRepository.save(user);

        return UserResponse.from(saveduser);

    }

    public LoginResponse login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new LoginFailedException());

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new LoginFailedException();
        }

        if (user.getDeletedAt() != null) {
            throw new LoginFailedException();
        }

        String token = jwtProvider.createToken(user.getUserId(), user.getLoginId());

        return new LoginResponse(token, UserResponse.from(user));

    }

    public LoginResponse kakaoLogin(String code){
        String kakaoAccessToken = kakaoAuthClient.getAccessToken(code);

        KakaoUserInfo userInfo = kakaoAuthClient.getUserInfo(kakaoAccessToken) ;
        String providerUid = String.valueOf(userInfo.getId());
        String email = (userInfo.getKakaoAccount() != null && userInfo.getKakaoAccount().getEmail() != null)
                ? userInfo.getKakaoAccount().getEmail()
                : "kakao_" + providerUid + "@kakao.com";
        String nickname = userInfo.getKakaoAccount() != null
                ? userInfo.getKakaoAccount().getProfile().getNickname()
                : "kakao_" + providerUid;
        String profileUrl = userInfo.getKakaoAccount() != null
                ? userInfo.getKakaoAccount().getProfile().getProfileImageurl()
                : null;

        User user = userRepository.findByProviderAndProviderUid("KAKAO", providerUid)
                .orElseGet(() -> userRepository.save(
                        User.ofKakao(providerUid, nickname, email, profileUrl)
                ));

        if (user.getDeletedAt() != null) {
            throw new LoginFailedException();
        }

        String token = jwtProvider.createToken(user.getUserId(), user.getLoginId());
        return new LoginResponse(token, UserResponse.from(user));
    }

    @Transactional
    public void sendPasswordResetCode(String loginId, String email) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("가입된 정보가 없습니다."));

        if (user.getProvider() != null) {
            throw new IllegalArgumentException("소셜 로그인 계정은 비밀번호를 변경할 수 없습니다.");
        }

        if (!email.equals(user.getEmail())) {
            throw new IllegalArgumentException("가입된 정보가 없습니다.");
        }

        String code = String.format("%06d", new SecureRandom().nextInt(1_000_000));
        resetCodeRepository.save(PasswordResetCode.of(loginId, code));
        emailService.sendPasswordResetCode(email, code);
    }

    @Transactional
    public VerifyCodeResponse verifyCode(String loginId, String code) {
        PasswordResetCode resetCode = resetCodeRepository
                .findTopByLoginIdAndUsedFalseOrderByExpiresAtDesc(loginId)
                .orElseThrow(() -> new IllegalArgumentException("인증코드가 존재하지 않습니다."));

        if (resetCode.isExpired()) {
            throw new IllegalArgumentException("인증코드가 만료되었습니다.");
        }

        if (!resetCode.getCode().equals(code)) {
            throw new IllegalArgumentException("인증코드가 일치하지 않습니다.");
        }

        resetCode.markUsed();

        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("가입된 정보가 없습니다."));

        String resetToken = jwtProvider.createResetToken(user.getUserId());
        return new VerifyCodeResponse(resetToken);
    }

    @Transactional
    public void resetPassword(String resetToken, String newPassword) {
        Long userId = jwtProvider.parseResetToken(resetToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("가입된 정보가 없습니다."));

        user.changePassword(passwordEncoder.encode(newPassword));
    }
}