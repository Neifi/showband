package com.neifi.showband.band.actions;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.neifi.showband.band.Band;
import com.neifi.showband.band.Bands;
import com.neifi.showband.user.User;
import com.neifi.showband.user.actions.UserController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class BandAction {
	
	 private final Bands bands;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerBand( @RequestBody Band band){
		
		if(band == null) {
			return ResponseEntity.badRequest().build();
		}
		bands.save(band);
		URI bandLink = linkTo(UserController.class).slash(band.getName()).withSelfRel().toUri();

		return ResponseEntity.created(bandLink).build();
	}
	
//	private Band isBand(Form form) {
//		Object preBand = registerAction.register(form);
//		Band band =  preBand.equals(User.class) ? (Band)preBand : null;
//		return band;
//	}
}
