package com.anrisys.teamflow.chat.domain.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.anrisys.teamflow.chat.domain.model.Chat;

public interface ChatRepository {
	Chat save(Chat chat);
	Optional<Chat> findById(BigInteger id);
	Optional<List<Chat>> findAllByUserId(String userId);
}
