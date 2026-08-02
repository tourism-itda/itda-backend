package com.tourism.itda.user.repository;

import com.tourism.itda.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLoginId(String loginId);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);

    Optional<User> findByLoginId(String loginId);
    Optional<User> findByProviderAndProviderUid(String provider, String providerUid);
}
