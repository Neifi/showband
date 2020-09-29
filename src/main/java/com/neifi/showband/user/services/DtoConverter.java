package com.neifi.showband.user.services;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neifi.showband.security.jwt.JwtUserResponse;
import com.neifi.showband.user.Rol;
import com.neifi.showband.user.User;
import com.neifi.showband.user.shared.UserInfo;

@Component
public class DtoConverter{
	
	@Autowired private ModelMapper modelMapper;
	
	public UserInfo userInfoConverter(User user){
		
		return modelMapper.map(user,UserInfo.class);
	}

	public Object convertUserAndTokenToJwtUserResponse(User user, String token) {
		// TODO Auto-generated method stub
		return JwtUserResponse.jwtUserResponseBuilder()
				//.rol(user.getRole().stream().map(Rol::name).collect(Collectors.toSet()))
				.token(token)
				.build();
	}
	
	
}
