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
	private User(PublicID id, String email, String hashPassword, Instant createdAt) {
		super();
		this.id = id;
		this.email = email;
		this.hashPassword = hashPassword;
		this.createdAt = createdAt;
	}
	public static User fromDB(String publicId, String email, String hashPassword, Instant createdAt) {
		return new User(PublicID.from(publicId), email, hashPassword, createdAt);
	}
	public static User create(String email, String rawPassword, PasswordEncoder encoder) {
		Instant created_at = Instant.now();
		return new User(PublicID.generate(), email, encoder.encode(rawPassword), created_at);
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
