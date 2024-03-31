package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.user.model.User;

import java.util.Optional;



public interface UserRepository extends JpaRepository<User,Integer>{

	
	
//	Optional<User> findByUsername(String username);
	
	
	
	
}
