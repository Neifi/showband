package com.neifi.showband.user;

import java.sql.Array;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.tomcat.jni.Time;
import org.hibernate.annotations.ListIndexBase;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neifi.showband.band.Band;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

@Builder

@Entity
@Component
@Table( schema = "public",name = "\"user\"")
public class User extends RepresentationModel <User> implements UserDetails{

	private static final long serialVersionUID = -1447772380092110687L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;
	private String bio;
	
	@Column(name="email")
	private String username;
	private String password;
	private String country;
	private String city;
	private float latitude;
	private float longitude;

//	@ElementCollection(fetch = FetchType.EAGER)
//	@Enumerated(EnumType.STRING)
//
//	private Set<Rol> role;
	
	
	@CreatedDate
	private LocalDateTime creation_date;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Stream.of(new SimpleGrantedAuthority("ADMIN")).collect(Collectors.toSet());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	//@ManyToOne
	/*@JoinColumn(name="bandId")
	private Band band;*/

	
}
