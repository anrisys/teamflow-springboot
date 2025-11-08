package com.anrisys.teamflow.auth.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anrisys.teamflow.auth.infrastructure.entity.UserEntity;

public interface SpringDataAuthRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPublicId(UUID publicId);
    Optional<UserEntity> findByRefreshTokenHash(String refreshTokenHash);
    boolean existsByEmail(String email);
}
