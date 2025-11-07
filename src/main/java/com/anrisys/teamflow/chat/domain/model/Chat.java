package com.anrisys.teamflow.chat.domain.model;

import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

public class Chat {
	private final BigInteger id;
	private final UUID user_1;
	private final UUID user_2;
	private final Instant createdAt;
	private String lastMessagePreview;
	private Instant lastMessageTimestamp;
	protected Chat(BigInteger id, UUID user_1, UUID user_2, Instant createdAt,
			String last_message_preview, Instant last_message_timestamp) {
		super();
		this.id = id;
		this.user_1 = user_1;
		this.user_2 = user_2;
		this.createdAt = createdAt;
		this.lastMessagePreview = last_message_preview;
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
	public void setLast_message_preview(String last_message_preview) {
		this.lastMessagePreview = last_message_preview;
	}
	public Instant getLast_message_timestamp() {
		return lastMessageTimestamp;
	}
	public void setLast_message_timestamp(Instant last_message_timestamp) {
		this.lastMessageTimestamp = last_message_timestamp;
	}
}
