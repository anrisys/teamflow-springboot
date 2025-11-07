package com.anrisys.teamflow.auth.app.service;

import org.springframework.stereotype.Service;

import com.anrisys.teamflow.auth.domain.model.LoginResult;
import com.anrisys.teamflow.auth.domain.model.User;
import com.anrisys.teamflow.auth.domain.repository.AuthRepository;
import com.anrisys.teamflow.auth.domain.service.JwtService;
import com.anrisys.teamflow.auth.exceptions.EmailAlreadyExistsException;
import com.anrisys.teamflow.auth.exceptions.InvalidCredentialsException;
import com.anrisys.teamflow.shared.security.PasswordEncoder;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthApplicationServiceImpl implements AuthApplicationService{

	private final AuthRepository authRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	private AuthApplicationServiceImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder,
			JwtService jwtService) {
		super();
		this.authRepository = authRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public User register(RegisterCommand command) {
		if (authRepository.existsByEmail(command.email())) {
			throw new EmailAlreadyExistsException();
		}
		
		User user = User.create(command.email(), command.password(), passwordEncoder);
		
		return authRepository.save(user);
	}

	@Override
	public LoginResult refreshToken(String refreshToken) {
		return jwtService.refreshTokens(refreshToken);	
	}

	@Override
	public void logout(String token) {
		jwtService.invalidateToken(token);
	}

	@Override
	public LoginResult login(LoginCommand command) {
		User user = authRepository.findByEmail(command.email()).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password "));
		
		if (!user.verifyPassword(command.password(), passwordEncoder)) {
			throw new InvalidCredentialsException("Invalid email or password ");
		}
		
		return jwtService.generateTokens(user);
	}
}
