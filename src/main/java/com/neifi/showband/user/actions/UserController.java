package com.neifi.showband.user.actions;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neifi.showband.location.Location;
import com.neifi.showband.location.LocationService;
import com.neifi.showband.user.User;
import com.neifi.showband.user.exceptions.UserNotFoundException;
import com.neifi.showband.user.services.DtoConverter;
import com.neifi.showband.user.services.UserService;
import com.neifi.showband.user.shared.UserInfo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user/")

public class UserController {
	
	@Autowired
	private final UserService service;
	private final DtoConverter converter;
	private final LocationService locationService;
	
	//TODO oauth2 auth
	@PostMapping("login")
	private ResponseEntity<UserInfo> userLogin(){
		return null;
	}
	
	@GetMapping("{id}")
	private ResponseEntity<User> getUser(@PathVariable long id){
		
		User usr = isPresent(id);
		
		return ResponseEntity.ok(usr);
		
	}
	
	@GetMapping("list")
	private ResponseEntity<List<UserInfo>> getAllUsers() {
		List<User> usrs = service.findAll();
	
		if (usrs.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		List<UserInfo> res = mapToUserInfoWithLinks(usrs);
		return ResponseEntity.ok(res);

	}

	@GetMapping("nearest")
	private ResponseEntity<List<User>> findNearUsers(@RequestBody User user, @RequestParam int distance){
		Location location = new Location(user.getLongitude(), user.getLatitude());
		List<User> nearUsers = locationService
				//TODO cambiar ciudad por pais y ciudad o ciudad que 
				//corresponda al pais con id del usuario
				.findUserNearTo(user.getCity(), distance,location);
		return ResponseEntity.ok(nearUsers);
	}
	
	@PostMapping("register")
	private ResponseEntity<Links> registerUser(@RequestBody User user) {
		if (user == null) {
			return ResponseEntity.badRequest().build();
		}
		
		UserInfo usr = converter.userInfoConverter(service.save(user));
		Link usrLink = linkTo(UserController.class).slash(user.getUserId()).withSelfRel();
		usr.add(usrLink);
		return ResponseEntity.status(HttpStatus.CREATED).body(usr.getLinks());
	}
	
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<UserInfo> deleteUser(@PathVariable Long id){
		UserInfo usr = converter.userInfoConverter(isPresent(id));
		service.deleteById(id);
		return ResponseEntity.ok(usr);
	}

	private User isPresent(Long id) {
		User usr = service.findById(id).isPresent()
				?service
						.findById(id)
						.orElseThrow
						(UserNotFoundException::new):null;
		
		return usr;
	}
	
	private List<UserInfo> mapToUserInfoWithLinks(List<User> usrs) {
		Link link = linkTo(UserController.class).withSelfRel();
		
		List<UserInfo> res = new ArrayList<UserInfo>();
		CollectionModel<User> coleCollectionModel = new CollectionModel<User>(usrs, link);
		
		coleCollectionModel
		.forEach(u -> res.add(converter.userInfoConverter(u)));
		res.forEach(u -> u.add((linkTo(UserController.class).slash(u.getUserId()).withSelfRel())));
			
		
		return res;
	}
	
	
}