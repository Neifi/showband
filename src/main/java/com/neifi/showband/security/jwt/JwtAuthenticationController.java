package com.neifi.showband.security.jwt;



import javax.naming.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neifi.showband.user.User;
import com.neifi.showband.user.services.DtoConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController{
	
	@Autowired private  AuthenticationManager authenticationManager;
	private final JwtProvider provider;
	private final DtoConverter converter;

	@PostMapping("auth/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws AuthenticationException{
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken
				(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = (User) authentication.getPrincipal();

		String token = provider.generateToken(authentication);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(converter.convertUserAndTokenToJwtUserResponse(user, token));
	}

}
