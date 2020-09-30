package com.neifi.showband.security.jwt;

import java.util.Set;

import com.neifi.showband.user.shared.JwtUserInfo;
import com.neifi.showband.user.shared.UserInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class JwtUserResponse extends JwtUserInfo{
	private String token;
	
	@Builder(builderMethodName = "jwtUserResponseBuilder")
	public JwtUserResponse(String username, String password,String avatar, String role, String token) {
		super(username,role,token);
		this.token = token;
	}
}


