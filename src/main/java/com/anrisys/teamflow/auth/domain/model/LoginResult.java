package com.anrisys.teamflow.auth.domain.model;

import java.time.Instant;

public record LoginResult(
	    String userId,
	    String email,
	    String accessToken,
	    String refreshToken,
	    Instant accessTokenExpiresAt,
	    Instant refreshTokenExpiresAt
	) {
	    // Domain methods - business logic
	    public boolean isAccessTokenExpired() {
	        return Instant.now().isAfter(accessTokenExpiresAt);
	    }
	    
	    public boolean isValid() {
	        return userId != null && !userId.isBlank() && 
	               accessToken != null && !accessToken.isBlank();
	    }
	}