package com.example.user.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.user.repository.UserRepository;



@Service 
public class UserDetailsServiceImp implements UserDetailsService{
	
	
	private final UserRepository userRepository ;
	
	public UserDetailsServiceImp (UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	

	
	
	
    // Implementation of the loadUserByUsername method from UserDetailsService interface
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		/*=============================================================================
		 *  Call the findByUsername method of UserRepository to find a users by user name
         * If user is found, return the UserDetails object representing the user
         * If user is not found, throw a UsernameNotFoundException
         ==============================================================================*/

		return userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
	}

}
