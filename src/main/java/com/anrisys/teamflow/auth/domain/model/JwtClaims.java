package com.anrisys.teamflow.auth.domain.model;

import java.util.Map;

public record JwtClaims(String userId, String email) {
    public Map<String, Object> toClaims() {
        return Map.of(
            "userId", userId,
            "email", email
            
        );
    }
    
    public static JwtClaims fromJwtClaims(io.jsonwebtoken.Claims claims) {
        return new JwtClaims(
            claims.get("userId", String.class),
            claims.get("email", String.class)
        );
    }
}
