package com.neifi.showband.location.formulas;

import com.neifi.showband.location.Location;

import lombok.Getter;


public abstract class Haversine {

	private final float EARTH_RADIUS = 6378.0f;
	private float km;
	
	protected float calculateDistanceInKm(Location or, Location dest) {
		
		float latDif = calculateLatDifference(or, dest);
		float longDif = calculateLongDifference(or, dest);
		float linearDistance = calculateLinearDistance(or, latDif, longDif);
		float angleDist = calculateAngleOfDistance(linearDistance);
		
		return angleDist*EARTH_RADIUS;
	}

	private float calculateAngleOfDistance(float linearDistance) {
		float angleDist = (float) Math.atan2(Math.sqrt(linearDistance),Math.sqrt(1-linearDistance));
		return angleDist;
	}

	private float calculateLinearDistance(Location or, float latDif, float longDif) {
		float linearDistance = (float) ((float) Math.pow(Math.sin(latDif/2),2)+
				Math.toRadians(Math.cos(or.getLatitude()))*
				Math.toRadians(Math.cos(or.getLatitude()))*
				Math.sin(Math.pow(longDif/2,2)));
		return linearDistance;
	}

	private float calculateLongDifference(Location or, Location dest) {
		float longDif = (float) Math.toRadians(dest.getLongitude() - or.getLongitude());
		return longDif;
	}

	private float calculateLatDifference(Location or, Location dest) {
		float latDif = (float) Math.toRadians(dest.getLatitude() - or.getLatitude());
		return latDif;
	}
	
}
