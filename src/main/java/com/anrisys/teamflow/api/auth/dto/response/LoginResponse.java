package com.anrisys.teamflow.api.auth.dto.response;

import java.time.Instant;

public record LoginResponse(
    String userId,
    String email,
    String accessToken,
    String refreshToken,
    String tokenType,
    Instant expiresAt,
    Instant refreshTokenExpiresAt
) {
    public static LoginResponse of(String userId, String email, String accessToken, 
                                 String refreshToken, Instant expiresAt, Instant refreshTokenExpiresAt) {
        return new LoginResponse(
            userId,
            email,
            accessToken,
            refreshToken,
            "Bearer",
            expiresAt,
            refreshTokenExpiresAt
        );
    }
    public static LoginResponse of(String userId, String email, String accessToken, 
            String refreshToken, Instant expiresAt) {
		return new LoginResponse(
			userId,
			email,
			accessToken,
			refreshToken,
			"Bearer",
			expiresAt,
			null
		);
    }
}
