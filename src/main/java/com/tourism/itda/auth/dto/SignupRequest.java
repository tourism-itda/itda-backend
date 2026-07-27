package com.tourism.itda.auth.dto;

import java.time.LocalDate;

public record SignupRequest (
        String loginId,
        String password,
        String name,
        String nickname,
        String email,
        LocalDate birthDate,
        Boolean agreedToTerms
){
}
