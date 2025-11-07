package com.anrisys.teamflow.auth.domain.repository;

import java.util.Optional;

import com.anrisys.teamflow.auth.domain.model.User;

public interface UserRepository {
	User save(User user);
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
}
