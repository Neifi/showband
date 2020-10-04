package com.neifi.showband.user.shared;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.neifi.showband.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor 
@NoArgsConstructor
@Data

public class UserInfo extends RepresentationModel<UserInfo>{
	
	private Long userId;
	private String name;
	private String bio;    
	private String username;
	private String country;
	private String city;

}
