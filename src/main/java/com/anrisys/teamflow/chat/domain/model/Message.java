package com.anrisys.teamflow.chat.domain.model;

import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

public class Message {
	private final UUID id;
	private final BigInteger chatId;
	private final UUID sender;
	private final UUID recepient;
	private final Instant createdAt;
	private String content;
	protected Message(UUID id, BigInteger chatId, UUID sender, UUID recepient, Instant createdAt,
			String content) {
		super();
		this.id = id;
		this.chatId = chatId;
		this.sender = sender;
		this.recepient = recepient;
		this.createdAt = createdAt;
		this.content = content;
	}
	public UUID getId() {
		return id;
	}
	public BigInteger getChatId() {
		return chatId;
	}
	public UUID getSender() {
		return sender;
	}
	public UUID getRecepient() {
		return recepient;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
