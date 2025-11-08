package com.anrisys.teamflow.auth.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.anrisys.teamflow.auth.domain.model.User;
import com.anrisys.teamflow.auth.infrastructure.entity.UserEntity;

@Component
public class UserMapper {
	public UserEntity toEntity(User user) {
		UserEntity entity = new UserEntity();
		entity.setEmail(user.getEmail());
		entity.setHashPassword(user.getHashPassword());
		entity.setCreatedAt(user.getCreatedAt());
		return entity;
	}
	
	public User toDomain(UserEntity entity) {
		return User.fromDB(
				entity.getPublicId().toString(),
				entity.getEmail(),
				entity.getHashPassword(),
				entity.getCreatedAt(),
				entity.getRefreshTokenHash(),
				entity.getRefreshTokenExpiresAt()
		);
	}
}
