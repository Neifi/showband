package com.neifi.showband.user.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserInfo {

	private String username;
	private String user_role;
	private String token;
	

}
