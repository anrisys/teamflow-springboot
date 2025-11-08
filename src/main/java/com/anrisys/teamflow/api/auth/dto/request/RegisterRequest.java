package com.anrisys.teamflow.api.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public record RegisterRequest(
		@NotBlank @Email String email, 
		@NotBlank @Size(min = 8) String password, 
		@NotBlank String confirmPassword) {

}
