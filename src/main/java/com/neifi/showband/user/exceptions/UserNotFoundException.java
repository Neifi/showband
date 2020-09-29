package com.neifi.showband.user.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 5122033260162565651L;

	public UserNotFoundException() {
		super("No se ha encontrado al cliente");
		
	}
}
