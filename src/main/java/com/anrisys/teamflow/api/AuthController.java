package com.anrisys.teamflow.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anrisys.teamflow.api.auth.dto.RegisterRequest;
import com.anrisys.teamflow.api.auth.dto.request.LoginRequest;
import com.anrisys.teamflow.api.auth.dto.request.RefreshTokenRequest;
import com.anrisys.teamflow.api.auth.dto.response.LoginResponse;
import com.anrisys.teamflow.api.auth.dto.response.RegisterResponse;
import com.anrisys.teamflow.auth.app.service.AuthApplicationService;
import com.anrisys.teamflow.auth.app.service.LoginCommand;
import com.anrisys.teamflow.auth.app.service.RegisterCommand;
import com.anrisys.teamflow.auth.domain.model.LoginResult;
import com.anrisys.teamflow.shared.common.ApiSuccessResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthApplicationService authApplicationService;

	protected AuthController(AuthApplicationService authApplicationService) {
		super();
		this.authApplicationService = authApplicationService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiSuccessResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request) {
		RegisterCommand command = new RegisterCommand(request.email(), request.password(), request.confirmPassword());
		
		authApplicationService.register(command);
		
		ApiSuccessResponse<RegisterResponse> response = ApiSuccessResponse.created("User registered successfully", RegisterResponse.create());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiSuccessResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
	    LoginCommand command = new LoginCommand(request.email(), request.password());
	    LoginResult result = authApplicationService.login(command);
	    
	    LoginResponse loginData = LoginResponse.of(
	        result.userId(),
	        result.email(),
	        result.accessToken(),
	        result.refreshToken(),
	        result.accessTokenExpiresAt(),
	        result.refreshTokenExpiresAt()
	    );
	    
	    ApiSuccessResponse<LoginResponse> response = 
	        ApiSuccessResponse.success("Login successful", loginData);
	    
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<ApiSuccessResponse<LoginResponse>> refreshToken(
	        @Valid @RequestBody RefreshTokenRequest request) {
	    
	    LoginResult result = authApplicationService.refreshToken(request.refreshToken());
	    
	    LoginResponse loginData = LoginResponse.of(
	        result.userId(),
	        result.email(),
	        result.accessToken(),
	        result.refreshToken(),
	        result.accessTokenExpiresAt()
	    );
	    
	    ApiSuccessResponse<LoginResponse> response = 
	        ApiSuccessResponse.success("Token refreshed successfully", loginData);
	    
	    return ResponseEntity.ok(response);
	}
	
}
