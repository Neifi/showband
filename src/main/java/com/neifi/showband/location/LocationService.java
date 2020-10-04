package com.neifi.showband.location;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neifi.showband.location.formulas.GeoLocation;
import com.neifi.showband.user.User;
import com.neifi.showband.user.UserRepository;


@Service
public class LocationService{
	
	@Autowired UserRepository repository;
	private final float EARTH_RADIUS = 6378.01f;

	
	
	public List<User> findUserNearTo(User user, double distance){
		GeoLocation location = GeoLocation.fromDegrees(user.getLatitude(), user.getLongitude());
		
		
		GeoLocation[] boundingCoordinates =
				location.boundingCoordinates(distance, EARTH_RADIUS);
		
			boolean meridian180WithinDistance =
				boundingCoordinates[0].getLongitudeInRadians() >
				boundingCoordinates[1].getLongitudeInRadians();
				
		
		double lat = boundingCoordinates[0].getLatitudeInDegrees();
		double lat2 = boundingCoordinates[1].getLatitudeInDegrees();
		double long1 = boundingCoordinates[0].getLongitudeInDegrees();
		double long2 = boundingCoordinates[1].getLongitudeInDegrees();
		
		double latRadians = location.getLatitudeInDegrees();
		double longRadians = location.getLongitudeInDegrees();
		double distRadius = distance/EARTH_RADIUS;
		
		
		List<User> nearUsers = repository.findUsersNearToRange(lat, lat2, long1,long2, 
				 latRadians, latRadians, longRadians, distRadius);
		
		return nearUsers;
	}
	

}
