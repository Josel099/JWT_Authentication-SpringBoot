package com.example.user.model;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table
public class User implements UserDetails{// Defines the User class and implements UserDetails interface for Spring Security
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String password;
	
	@Enumerated(value=EnumType.STRING) // Specifies the type of enum used for the 'role' field
	Role role; // Represents the user's role
	

	// Default constructor 
	public User() {
	super();
	}

	
	// Parameterized constructor
	public User(int id, String firstName, String lastName, String userName, String password, Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	
	//getters and setters 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
	
	
	// Override methods from UserDetails interface
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Return a collection of granted authorities for the user (e.g., roles)
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		// Return the user name of the user
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// Return whether the user's account is not expired
		return true; // Assuming the account never expires
	}

	@Override
	public boolean isAccountNonLocked() {
	// Return whether the user's account is not locked
		return true; // Assuming the account is never locked
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Return whether the user's credentials are not expired
		return true; // Assuming the credentials never expire
	}

	@Override
	public boolean isEnabled() {
		// Return whether the user's account is enabled
		return true; // Assuming the account is always enabled}
	
	}
	

}
