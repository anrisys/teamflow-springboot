package com.anrisys.teamflow.auth.infrastructure.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@Column(name = "user_id")
	private Integer userId;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(name = "hash_password", nullable = false)
	private String hashPassword;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	public UserEntity() {
		super();
	}
	
	public UserEntity(Integer userId, String email, String hashPassword, Instant createdAt) {
		super();
		this.userId = userId;
		this.email = email;
		this.hashPassword = hashPassword;
		this.createdAt = createdAt;
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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}	
}
