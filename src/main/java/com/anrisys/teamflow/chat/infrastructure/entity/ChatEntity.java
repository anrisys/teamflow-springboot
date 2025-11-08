package com.anrisys.teamflow.chat.infrastructure.entity;

import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chats")
public class ChatEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private BigInteger id;
	
	@Column(name = "user_1", nullable = false, updatable = false)
	private UUID user_1;
	
	@Column(name = "user_2", nullable = false, updatable = false)
	private UUID user_2;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;
	
	@Column(name = "last_message_preview", nullable = false, updatable = false)
	private String lastMessagePreview;
	
	@Column(name = "last_message_timestamp", nullable = false, updatable = false)
	private Instant lastMessageTimestamp;
	
	protected ChatEntity() {}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setUser_1(UUID user_1) {
		this.user_1 = user_1;
	}

	public void setUser_2(UUID user_2) {
		this.user_2 = user_2;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public void setLast_message_preview(String last_message_preview) {
		this.lastMessagePreview = last_message_preview;
	}

	public void setLast_message_timestamp(Instant last_message_timestamp) {
		this.lastMessageTimestamp = last_message_timestamp;
	}

	public BigInteger getId() {
		return id;
	}

	public UUID getUser_1() {
		return user_1;
	}

	public UUID getUser_2() {
		return user_2;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public String getLast_message_preview() {
		return lastMessagePreview;
	}

	public Instant getLast_message_timestamp() {
		return lastMessageTimestamp;
	}
	
}
