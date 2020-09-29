package com.neifi.showband.comments;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.neifi.showband.band.Band;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Comment {
	
	@Id
	private Long commentId;
	private String text;
	
    @ElementCollection(targetClass=String.class)
	private List<String> replies;
	private int votesUp;
	private int votesDown;
	@ManyToOne
	@JoinColumn(name="bandId")
	private Band band;
}
