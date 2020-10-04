package com.neifi.showband.user.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neifi.showband.security.jwt.JwtUserResponse;
import com.neifi.showband.user.User;
import com.neifi.showband.user.shared.UserInfo;
import com.neifi.showband.user.shared.UserLink;

@Component
public class DtoConverter{
	
	@Autowired private ModelMapper modelMapper;
	
	public UserInfo userInfoConverter(User user){
		
		return modelMapper.map(user,UserInfo.class);
	}
	
public UserLink userLinkConverter(User user){
		
		return modelMapper.map(user,UserLink.class);
	}
	
	public Object convertUserAndTokenToJwtUserResponse(User user, String token) {
		
		return JwtUserResponse.jwtUserResponseBuilder()
				.username(user.getUsername())
				.role(user.getUser_role())
				.token(token)
				.build();
	}
	
	
}
