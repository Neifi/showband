package com.neifi.showband.user.actions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neifi.showband.location.LocationService;
import com.neifi.showband.user.User;
import com.neifi.showband.user.exceptions.UserNotFoundException;
import com.neifi.showband.user.services.DtoConverter;
import com.neifi.showband.user.services.UserService;
import com.neifi.showband.user.shared.UserInfo;
import com.neifi.showband.user.shared.UserLink;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user/")

public class UserController {
	
	@Autowired
	private final UserService service;
	private final DtoConverter converter;
	private final LocationService locationService;
	private final PasswordEncoder passwordEncoder;
	//TODO oauth2 auth
	@PostMapping("login")
	private ResponseEntity<UserInfo> userLogin(){
		return null;
	}
	
	@GetMapping("{id}")
	private ResponseEntity<UserInfo> getUser(@PathVariable long id){
		
		User usr = isPresent(id);
		
		return ResponseEntity.ok(converter.userInfoConverter(usr));
		
	}
	
	@GetMapping("list")
	private ResponseEntity<List<UserLink>> getAllUsers() {
		List<User> usrs = service.findAll();
		
	
		if (usrs.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(mapToUserLink(usrs));

	}

	@GetMapping("nearest")
	private ResponseEntity<List<UserLink>> findNearUsers(@RequestBody User user, @RequestParam int distance){
		List<User> nearUsers = locationService.findUserNearTo(user, distance);
		
		return ResponseEntity.ok(mapToUserLink(nearUsers));
	}
	
	@PostMapping("register")
	private ResponseEntity<Links> registerUser(@RequestBody User user) {
		if (user == null) {
			return ResponseEntity.badRequest().build();
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreation_date(LocalDateTime.now());
		user.setUser_role("ADMIN");
		service.save(user);
		UserInfo usr = converter.userInfoConverter(user);
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
	
	private List<UserLink> mapToUserLink(List<User> usrs) {
		Link link = linkTo(UserController.class).withSelfRel();
		
		List<UserLink> res = new ArrayList<UserLink>();

		CollectionModel<User> coleCollectionModel = new CollectionModel<User>(usrs, link);
		
		coleCollectionModel
		.forEach(u -> res.add(converter.userLinkConverter(u)));
		
		res.forEach(u -> u.add((linkTo(UserController.class).slash(u.getUserId()).withSelfRel())));
			
		
		return res;
	}
	
	
}
