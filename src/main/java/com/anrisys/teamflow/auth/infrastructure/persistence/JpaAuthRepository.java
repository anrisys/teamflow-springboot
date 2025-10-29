package com.anrisys.teamflow.auth.infrastructure.persistence;

import java.util.Optional;

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

}
