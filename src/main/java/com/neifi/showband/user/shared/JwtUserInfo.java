package com.neifi.showband.user.shared;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserInfo {

	private String username;
	private String roles;
	private String token;
	

}
