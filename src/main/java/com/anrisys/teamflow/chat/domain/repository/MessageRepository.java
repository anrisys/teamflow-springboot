package com.anrisys.teamflow.chat.domain.repository;

import java.util.List;
import java.util.Optional;

import com.anrisys.teamflow.chat.domain.model.Message;

public interface MessageRepository {
	Message save(Message message);
	Optional<List<Message>> findAllByChatId(String chatId);
}
