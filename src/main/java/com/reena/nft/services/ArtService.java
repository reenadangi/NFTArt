package com.reena.nft.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reena.nft.models.Art;
import com.reena.nft.models.Rating;
import com.reena.nft.repositories.ArtRepository;
import com.reena.nft.repositories.RatingRepository;
import com.reena.nft.repositories.UserRepository;

@Service
public class ArtService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ArtRepository artRepository;
	
	@Autowired
	private RatingRepository ratingRepository;
	
//	Collection of arts
	public List<Art> allArts(){
		return artRepository.findAll();
	}
//	Add
	
	public Art createArt(Art art) {
		return artRepository.save(art);
	}
	
//	Update
	public Art updateArt(Art art) {
		return artRepository.save(art);
	}
//	Delete
	public void deleteArt(Long id) {
		artRepository.deleteById(id);
		
	}
//	Get One Art Detail 
	public Art artDetails(Long id) {
		return artRepository.findById(id).orElse(null);
	}
//	Add Rating to Art 
	public void AddRating(Rating rating) {
		 ratingRepository.save(rating);
	}
	
}
