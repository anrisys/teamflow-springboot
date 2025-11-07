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
@Table(name = "messages")
public class MessageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "chat_id", nullable = false, updatable = false)
	private BigInteger chatId;
	
	@Column(name = "sender", nullable = false, updatable = false)
	private UUID sender;
	
	@Column(name = "recipient", nullable = false, updatable = false)
	private UUID recipient;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	public MessageEntity() {}
	
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
		return recipient;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public String getContent() {
		return content;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public void setChatId(BigInteger chatId) {
		this.chatId = chatId;
	}
	public void setSender(UUID sender) {
		this.sender = sender;
	}
	public void setRecepient(UUID recepient) {
		this.recipient = recepient;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
