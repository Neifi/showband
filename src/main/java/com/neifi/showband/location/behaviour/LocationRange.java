

package com.neifi.showband.location.behaviour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.neifi.showband.location.LocationService;
import com.neifi.showband.user.User;
import com.sun.xml.bind.v2.runtime.Location;

public class LocationRange {
	
	@Autowired LocationService locationService;
	
	public List<User> findNearTo(String city){
		
		return null;
	}
}
