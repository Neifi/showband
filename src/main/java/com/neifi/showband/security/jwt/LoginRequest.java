package com.neifi.showband.security.jwt;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class LoginRequest {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
