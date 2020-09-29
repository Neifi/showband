package com.neifi.showband.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	
	List<User> findByCity(String city);

	Optional<User> findByUsername(String username);

	
	//SELECT * FROM public.user WHERE country = (SELECT country FROM public.user WHERE id = x) 
	//AND city = (SELECT city FROM public.user WHERE id = x) AND latitude BETWEN x AND x
}
