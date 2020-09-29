package com.neifi.showband.band;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.neifi.showband.comments.Comment;
import com.neifi.showband.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
public class Band {
	
	@Id
	private Long bandId;
	private String name;
	private String desc;
	
    @ElementCollection(targetClass=String.class)
	private List<String> genres;
	
//	@OneToMany(targetEntity=User.class, mappedBy="band")
//	private List<User> integrants;
	private int rate;
	@OneToMany(targetEntity=Comment.class, mappedBy="band")
	private List<Comment> comments;
	
}
