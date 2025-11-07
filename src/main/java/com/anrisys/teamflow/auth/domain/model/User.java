package com.anrisys.teamflow.auth.domain.model;

import java.time.Instant;
import java.util.Objects;
import com.anrisys.teamflow.shared.common.PublicID;
import com.anrisys.teamflow.shared.security.PasswordEncoder;

public class User {
	private final PublicID id;
	private final String email;
	private String hashPassword;
	private final Instant createdAt;
	private String refreshTokenHash;
	private Instant refreshTokenExpiresAt;
	private User(PublicID id, String email, String hashPassword, Instant createdAt, String refreshTokenHash,
			Instant refreshTokenExpiresAt) {
		super();
		this.id = id;
		this.email = email;
		this.hashPassword = hashPassword;
		this.createdAt = createdAt;
		this.refreshTokenHash = refreshTokenHash;
		this.refreshTokenExpiresAt = refreshTokenExpiresAt;
	}
	public static User fromDB(String publicId, String email, String hashPassword, Instant createdAt, String refreshTokenHash,
			Instant refreshTokenExpiresAt) {
		return new User(PublicID.from(publicId), email, hashPassword, createdAt, refreshTokenHash, refreshTokenExpiresAt);
	}
	public static User create(String email, String rawPassword, PasswordEncoder encoder) {
		Instant created_at = Instant.now();
		return new User(PublicID.generate(), email, encoder.encode(rawPassword), created_at, null, null);
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
	public boolean verifyPassword(String rawPassword, PasswordEncoder encoder) {
		return encoder.matches(rawPassword, this.hashPassword);
	}
	public String getHashPassword() {
		return hashPassword;
	}
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}
	public PublicID getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
