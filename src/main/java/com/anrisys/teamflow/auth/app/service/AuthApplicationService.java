package com.anrisys.teamflow.auth.app.service;

import com.anrisys.teamflow.auth.domain.model.User;

public interface AuthApplicationService {
	User register(RegisterCommand command);
}
