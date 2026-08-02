package com.tourism.itda.auth.service;

import com.tourism.itda.auth.dto.KakaoUserInfo;
import com.tourism.itda.auth.dto.LoginRequest;
import com.tourism.itda.auth.dto.LoginResponse;
import com.tourism.itda.auth.dto.UserResponse;
import com.tourism.itda.auth.exception.DuplicateLoginIdException;
import com.tourism.itda.auth.exception.DuplicateEmailException;
import com.tourism.itda.auth.exception.DuplicateNicknameException;
import com.tourism.itda.auth.exception.LoginFailedException;
import com.tourism.itda.global.client.KakaoAuthClient;
import com.tourism.itda.global.jwt.JwtProvider;
import com.tourism.itda.user.entity.User;
import com.tourism.itda.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    // 아래 생성자로 "주입"받아서 씀 (직접 new BCryptPasswordEncoder() 하지 않음)
    private final KakaoAuthClient kakaoAuthClient;

    // 생성자 주입: Spring이 이 생성자를 보고 필요한 Bean들을 자동으로 넣어줌
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, KakaoAuthClient kakaoAuthClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.kakaoAuthClient = kakaoAuthClient;
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

        String token = jwtProvider.createToken(user.getUserId(), user.getLoginId());
        return new LoginResponse(token, UserResponse.from(user));
    }
}