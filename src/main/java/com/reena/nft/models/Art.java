package com.reena.nft.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="arts")
public class Art {

	 @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	 
	  @NotBlank
	    @Size(min = 2, max = 200, message="Art Name should be between 2-200")
		private String artName;
		
	 @NotBlank
	    @Size(min = 2, max = 255, message="Description Name should be between 2-200")
		private String description;
		
	 @ManyToOne( fetch=FetchType.LAZY)
	 @JoinColumn(name="user_id")
	 private User artist;
	 
	 
	 @OneToMany(mappedBy ="ratedArt", fetch=FetchType.LAZY)
	 private List<Rating> ratings;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getArtName() {
		return artName;
	}


	public void setArtName(String artName) {
		this.artName = artName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public User getArtist() {
		return artist;
	}


	public void setArtist(User artist) {
		this.artist = artist;
	}


	public List<Rating> getRatings() {
		return ratings;
	}


	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	 
	public float getAvgRating() {
		float sum=0;
		for (Rating rating : ratings) {
			sum+=rating.getRating();
		}
		if(sum!=0) {
			sum=sum/this.ratings.size();
			return sum;
		}
		else {
			// TODO: handle exception
			System.out.print("In excption block");
			float x=0.0f;
			return x;
		}
		
		
		
	}
	
}
