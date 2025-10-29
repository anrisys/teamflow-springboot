package com.anrisys.teamflow.auth.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anrisys.teamflow.auth.infrastructure.entity.UserEntity;

public interface SpringDataAuthRepository extends JpaRepository<UserEntity, Integer> {
	Optional<UserEntity> findByEmail(String email);
	boolean existsByEmail(String email);
}
