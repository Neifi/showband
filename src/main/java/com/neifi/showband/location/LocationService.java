package com.neifi.showband.location;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neifi.showband.location.formulas.Haversine;
import com.neifi.showband.user.User;
import com.neifi.showband.user.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class LocationService extends Haversine{
	
	@Autowired UserRepository repository;
	
	
	
	public List<User> findUserNearTo(User user, int distance){
		List<User>  usersInCity = repository.findByCity(user.getCity()).stream()
				.filter(u -> isInRange(u, distance))
				.collect(Collectors.toList());

		return usersInCity;
	}
	
	
	private boolean isInRange(User user,int distance) {
		Location userLocation = new Location(user.getLongitude(), user.getLongitude());
		
		if(super.calculateDistanceInKm(userLocation, 
				new Location(user.getLongitude(),
						user.getLatitude())) < distance) {
			return true;
		}
		
		return false;
	}
	
}
