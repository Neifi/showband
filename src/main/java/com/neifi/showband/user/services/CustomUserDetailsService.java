package com.neifi.showband.user.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neifi.showband.user.User;

import lombok.RequiredArgsConstructor;

@Service("UserDetailService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		
		return userService.findByUsername(email)
				.orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado"));
	}

	public Optional<User> loadUserById(Long id) {

		return userService.findById(id);
	}



}