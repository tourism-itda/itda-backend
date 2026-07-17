package com.tourism.itda.auth.service;

import com.tourism.itda.auth.dto.UserResponse;
import com.tourism.itda.auth.exception.DuplicateLoginIdException;
import com.tourism.itda.auth.exception.DuplicateEmailException;
import com.tourism.itda.auth.exception.DuplicateNicknameException;
import com.tourism.itda.user.entity.User;
import com.tourism.itda.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // 아래 생성자로 "주입"받아서 씀 (직접 new BCryptPasswordEncoder() 하지 않음)

    // 생성자 주입: Spring이 이 생성자를 보고 필요한 Bean들을 자동으로 넣어줌
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse signup(String loginId, String password, String name, String nickname, String email,
                               LocalDate birthDate, Boolean agreedToTerms){

        if (userRepository.existsByLoginId(loginId)){
            throw new DuplicateLoginIdException(loginId);
        }

        if (userRepository.existsByNickname(nickname)){
            throw new DuplicateNicknameException(nickname);
        }

        if (userRepository.existsByEmail(email)){
            throw new DuplicateEmailException(email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(loginId, encodedPassword, name, nickname, email, birthDate, agreedToTerms);

        User saveduser = userRepository.save(user);

        return UserResponse.from(saveduser);

    }
}
