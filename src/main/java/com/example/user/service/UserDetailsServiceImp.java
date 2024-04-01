package com.example.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.user.repository.UserRepository;



@Service
public class UserDetailsServiceImp implements UserDetailsService{
	
	
	 @Autowired //custom annotation
	private final UserRepository userRepository ;
	
	// constructor 
	public UserDetailsServiceImp (UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	

	
	
	
    // Implementation of the loadUserByUsername method from UserDetailsService interface
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		/**=============================================================================
		 *  Call the findByUsername method of UserRepository to find a user by user name
         * If user is found, return the UserDetails object representing the user
         * If user is not found, throw a UsernameNotFoundException
         ==============================================================================*/

		return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
	}

}
