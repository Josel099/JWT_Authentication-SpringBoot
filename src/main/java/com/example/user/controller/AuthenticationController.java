package com.example.user.controller;

import com.example.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.AuthenticationResponse;
import com.example.user.service.AuthenticationService;

@RestController
public class AuthenticationController {

	
	private final AuthenticationService authService;
	 
	//constructor 
	public AuthenticationController(AuthenticationService authService) {
		super();
		this.authService = authService;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody User request){
		
		return ResponseEntity.ok(authService.register(request));
	}
	
	
	
	public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
		return ResponseEntity.ok(authService.authenticate(request));
	}
	
}
