package com.anrisys.teamflow.auth.domain.service;

import com.anrisys.teamflow.auth.domain.model.JwtClaims;
import com.anrisys.teamflow.auth.domain.model.LoginResult;
import com.anrisys.teamflow.auth.domain.model.User;

public interface JwtService {
	LoginResult generateTokens(User user);
	LoginResult refreshTokens(String refreshToken);
	void invalidateToken(String token);
	JwtClaims validateToken(String token);
}
