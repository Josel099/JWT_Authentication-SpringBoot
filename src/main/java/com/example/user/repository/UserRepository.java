package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.user.model.User;

import java.util.Optional;



public interface UserRepository extends JpaRepository<User,Integer>{

	
	/**=====================================================
	 * Declare a method to find a user by username
	 * Optional is used to represent a nullable result
	 * Method name follows Spring Data JPA naming conventions
	 ======================================================== */
	
	Optional<User> findByUsername(String username);
	
	
}
