package com.example.user.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.user.model.AuthenticationResponse;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;

@Service
// service class for the login and registration operations
public class AuthenticationService {

	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	
	//constructor 
	public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
			AuthenticationManager authenticationManager) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	
	
	// registering 
	public AuthenticationResponse register(User request) {
	
	User user = new User();
	user. setFirstName(request.getFirstName());
	user. setLastName(request.getLastName());
	user. setUserName (request.getUserName());
	user. setPassword (passwordEncoder. encode(request.getPassword()));
	user. setRole(user.getRole());
	user = userRepository. save(user);
	
	String token = jwtService.generateToken(user);
	return new AuthenticationResponse(token);
	
	}
	
	
	
	//login authentication
	public AuthenticationResponse authenticate(User request) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword()));
		
		
		User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		
		String token =  jwtService.generateToken(user);
		
		return new AuthenticationResponse(token);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
