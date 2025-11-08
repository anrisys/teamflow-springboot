package com.anrisys.teamflow.auth.domain.repository;

import java.time.Instant;
import java.util.Optional;

import com.anrisys.teamflow.auth.domain.model.User;

public interface AuthRepository {
	User save(User user);
	Optional<User> findByEmail(String email);
	Optional<User> findById(String userId);
	Optional<User> findByRefreshTokenHash(String refreshTokenHash);
	boolean existsByEmail(String email);
	void updateRefreshToken(String userId, String refreshTokenHash, Instant expiresAt);
	void clearRefreshToken(String userId);
}
