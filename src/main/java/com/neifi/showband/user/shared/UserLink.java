package com.neifi.showband.user.shared;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class UserLink extends RepresentationModel<UserLink>{
	private Long userId;
}
