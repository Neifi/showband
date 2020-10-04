package com.neifi.showband.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByCity(String city);

	Optional<User> findByUsername(String username);
	
	
	@Query(value = "SELECT * FROM public.user WHERE \n" + 
			"    (latitude >= ? AND latitude <= ?) AND (longitude >= ? AND longitude <= ?) \n" + 
			"    AND acos(sin(?) * sin(latitude) + cos(?) * cos(latitude) * cos(longitude - ?)) <= ? ORDER BY latitude DESC , longitude DESC", nativeQuery = true)

	List<User> findUsersNearToRange(@Param("lat1") double lat1, @Param("lat2") double lat2, @Param("long1") double long1,
			@Param("long2") double long2, @Param("latRadians") double latRadians,
			@Param("latRadians2") double latRadians2, @Param("longRadians") double longRadians,
			@Param("distance/radius") double distRadius);

}
