package com.neifi.showband.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByCity(String city);

	Optional<User> findByUsername(String username);

//	@Query(value = "	SELECT * FROM public.user u WHERE u.country = "
//			+ "(SELECT country FROM public.user u WHERE u.id = ?1)"
//			+ "AND u.city = "
//			+ "(SELECT city FROM public.user u WHERE u.id = ?1) "
//			+ "AND u.latitude BETWEN ?2 AND ?3"
//			+ "AND u.longitude BETWEEN ?4 AND ?5")
//	List<User> findUsersNearToRange(Long userId, float minLat, float maxLat, float minLong, float maxLong);

}
