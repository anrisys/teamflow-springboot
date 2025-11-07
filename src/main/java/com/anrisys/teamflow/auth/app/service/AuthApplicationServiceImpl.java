package com.anrisys.teamflow.auth.app.service;

import org.springframework.stereotype.Service;

import com.anrisys.teamflow.auth.domain.model.User;
import com.anrisys.teamflow.auth.domain.repository.AuthRepository;
import com.anrisys.teamflow.auth.exceptions.EmailAlreadyExistsException;
import com.anrisys.teamflow.shared.security.PasswordEncoder;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthApplicationServiceImpl implements AuthApplicationService{

	private final AuthRepository authRepository;
	private final PasswordEncoder passwordEncoder;
	public AuthApplicationServiceImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
		super();
		this.authRepository = authRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User register(RegisterCommand command) {
		if (authRepository.existsByEmail(command.email())) {
			throw new EmailAlreadyExistsException();
		}
		
		User user = User.create(command.email(), command.password(), passwordEncoder);
		
		return authRepository.save(user);
	}
}
