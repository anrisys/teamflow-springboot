package com.anrisys.teamflow.auth.infrastructure.persistence;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.anrisys.teamflow.auth.domain.model.User;
import com.anrisys.teamflow.auth.domain.repository.AuthRepository;
import com.anrisys.teamflow.auth.infrastructure.entity.UserEntity;
import com.anrisys.teamflow.auth.infrastructure.persistence.mapper.UserMapper;

public class JpaAuthRepository implements AuthRepository {
	private SpringDataAuthRepository springDataAuthRepository;
	private UserMapper mapper;

	protected JpaAuthRepository(SpringDataAuthRepository springDataAuthRepository, UserMapper mapper) {
		super();
		this.springDataAuthRepository = springDataAuthRepository;
		this.mapper = mapper;
	}

	@Override
	public User save(User user) {
		UserEntity entity = mapper.toEntity(user);
		UserEntity savedEntity = springDataAuthRepository.save(entity);
		return mapper.toDomain(savedEntity);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return springDataAuthRepository.findByEmail(email).map(mapper::toDomain);
	}

	@Override
	public boolean existsByEmail(String email) {
		return springDataAuthRepository.existsByEmail(email);
	}

	@Override
	public Optional<User> findById(String userId) {
		return springDataAuthRepository.findByPublicId(UUID.fromString(userId)).map(mapper::toDomain);
	}

	@Override
	public Optional<User> findByRefreshTokenHash(String refreshTokenHash) {
		return springDataAuthRepository.findByRefreshTokenHash(refreshTokenHash).map(mapper::toDomain);
	}

	@Override
	public void updateRefreshToken(String userId, String refreshTokenHash, Instant expiresAt) {
        springDataAuthRepository.findByPublicId(UUID.fromString(userId)).ifPresent(userEntity -> {
            userEntity.setRefreshTokenHash(refreshTokenHash);
            userEntity.setRefreshTokenExpiresAt(expiresAt);
            springDataAuthRepository.save(userEntity);
        });
	}

	@Override
	public void clearRefreshToken(String userId) {
        springDataAuthRepository.findByPublicId(UUID.fromString(userId)).ifPresent(userEntity -> {
            userEntity.setRefreshTokenHash(null);
            userEntity.setRefreshTokenExpiresAt(null);
            springDataAuthRepository.save(userEntity);
        });
	}

}
