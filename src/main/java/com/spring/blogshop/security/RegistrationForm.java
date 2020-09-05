package com.spring.blogshop.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.blogshop.entity.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationForm {
	private  Long id;
	private  String username;
	private  String password;
	private  String email;
	private  String phoneNumber;
	private  String city;
	private byte[] imageUserNew;
	
	public User toUser(PasswordEncoder passwordEncoder) {
	    return new User(
	        username, passwordEncoder.encode(password), 
	        email, phoneNumber, city,imageUserNew);
	  }
	
}
