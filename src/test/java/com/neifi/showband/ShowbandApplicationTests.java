package com.neifi.showband;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.neifi.showband.location.Location;
import com.neifi.showband.location.LocationService;
import com.neifi.showband.user.User;
import com.neifi.showband.user.UserRepository;
import com.neifi.showband.user.services.UserService;

@SpringBootTest
class ShowbandApplicationTests {

	@Autowired
	LocationService locationService;
	@Autowired UserRepository repo;
	@Test
	void contextLoads() {
	}

	@Test
	private void calculateDistance() {
		
		
		
	}
}
