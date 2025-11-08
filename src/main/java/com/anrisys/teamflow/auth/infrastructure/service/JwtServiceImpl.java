package com.anrisys.teamflow.auth.infrastructure.service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anrisys.teamflow.auth.domain.model.JwtClaims;
import com.anrisys.teamflow.auth.domain.model.LoginResult;
import com.anrisys.teamflow.auth.domain.model.User;
import com.anrisys.teamflow.auth.domain.repository.AuthRepository;
import com.anrisys.teamflow.auth.domain.service.JwtService;
import com.anrisys.teamflow.auth.exceptions.InvalidTokenException;
import com.anrisys.teamflow.auth.exceptions.TokenExpiredException;
import com.anrisys.teamflow.shared.security.PasswordEncoder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
	@Value("${app.jwt.secret}")
	private String secretKey;
	
	@Value("${app.jwt.access-token-expiration:3600}")
	private long accessTokenExpiration;
	
	@Value("${app.jwt.refresh-token-expiration:604800}")
	private long refreshTokenExpiration;
	
	private final AuthRepository authRepository;
	private final PasswordEncoder passwordEncoder;
	
	protected JwtServiceImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
		super();
		this.authRepository = authRepository;
		this.passwordEncoder = passwordEncoder;
	}

	private SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes();
        if (keyBytes.length < 32) {
            // Pad the key if it's too short
            byte[] paddedKey = new byte[32];
            System.arraycopy(keyBytes, 0, paddedKey, 0, Math.min(keyBytes.length, 32));
            keyBytes = paddedKey;
        }
        return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public LoginResult generateTokens(User user) {
		Instant now = Instant.now();
		Instant accessTokenExpiry = now.plusSeconds(accessTokenExpiration);
		Instant refreshTokenExpiry = now.plusSeconds(refreshTokenExpiration);
		
		String accessToken = buildAccessToken(user, now, accessTokenExpiry);
		
	    String refreshToken = buildRefreshToken(user.getId().toString(), now, refreshTokenExpiry);
	    
	    String refreshTokenHash = passwordEncoder.encode(refreshToken);
	    authRepository.updateRefreshToken(user.getId().toString(), refreshTokenHash, refreshTokenExpiry);
	    
	    return new LoginResult(
	    		user.getId().toString(),
	    		user.getEmail(),
	    		accessToken,
	    		refreshToken,
	    		accessTokenExpiry,
	    		refreshTokenExpiry
	    );
	}

	@Override
	public LoginResult refreshTokens(String refreshToken) {
		try {
			Jws<Claims> jws = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(refreshToken);
			
			Claims claims = jws.getPayload();
			String userId = claims.getSubject();
	        
//	        User user= authRepository.findById(userId)
//	            .orElseThrow(() -> new InvalidTokenException("User not found or inactive"));
	        
	        String refreshTokenHash = passwordEncoder.encode(refreshToken);
	        User user = authRepository.findByRefreshTokenHash(refreshTokenHash).orElseThrow(() -> new InvalidTokenException("Invalid refresh token"));
	        
	        if (user.getRefreshTokenExpiresAt() == null || Instant.now().isAfter(user.getRefreshTokenExpiresAt())) {
				throw new TokenExpiredException("Refresh token has expired");
			}
			
			return generateTokens(user);
		} catch (ExpiredJwtException e) {
			 throw new TokenExpiredException("Refresh token has expired", e);
		} catch (JwtException e) {
			throw new InvalidTokenException("Invalid refresh token", e);
		}
	}

	@Override
	public void invalidateToken(String token) {
		// TODO Auto-generated method stub
	}

	@Override
	public JwtClaims validateToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            
            Claims claims = jws.getPayload();
            return JwtClaims.fromJwtClaims(claims);
            
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Token has expired", e);
        } catch (JwtException e) {
            throw new InvalidTokenException("Invalid token", e);
        }
	}
	
	private String buildAccessToken(User user, Instant issuedAt, Instant expiresAt) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", user.getId().toString());
		claims.put("email", user.getEmail());
		
		return Jwts.builder().claims(claims)
        .issuedAt(Date.from(issuedAt))
        .expiration(Date.from(expiresAt))
        .signWith(getSigningKey())
        .compact();
	}
	
	private String buildRefreshToken(String userId, Instant issuedAt, Instant expiresAt) {
		return Jwts.builder()
			.subject(userId)
			.issuedAt(Date.from(issuedAt))
			.expiration(Date.from(expiresAt))
			.signWith(getSigningKey())
			.compact();
	}
}
