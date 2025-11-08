package com.anrisys.teamflow.auth.infrastructure.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "public_id", nullable = false, updatable = false)
	private UUID publicId;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(name = "hash_password", nullable = false)
	private String hashPassword;

	@Column(name = "refresh_token_hash")
	private String refreshTokenHash;

	@Column(name = "refresh_token_expires_at")
	private Instant refreshTokenExpiresAt;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	public UserEntity() {
		super();
	}

	public UserEntity(UUID publicId, String email, String hashPassword, Instant createdAt) {
		this.publicId = publicId;
		this.email = email;
		this.hashPassword = hashPassword;
		this.createdAt = createdAt;
	}

	public UUID getPublicId() {
		return publicId;
	}

	public void setPublicId(UUID publicId) {
		this.publicId = publicId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public String getRefreshTokenHash() {
		return refreshTokenHash;
	}

	public void setRefreshTokenHash(String refreshTokenHash) {
		this.refreshTokenHash = refreshTokenHash;
	}

	public Instant getRefreshTokenExpiresAt() {
		return refreshTokenExpiresAt;
	}

	public void setRefreshTokenExpiresAt(Instant refreshTokenExpiresAt) {
		this.refreshTokenExpiresAt = refreshTokenExpiresAt;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
}
