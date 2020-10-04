package com.neifi.showband.user.shared;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private float latitude;
	private float longitude;
	private LocalDateTime creation_date;
	private boolean enabled;
	Collection<? extends GrantedAuthority> authorities;
	
}
