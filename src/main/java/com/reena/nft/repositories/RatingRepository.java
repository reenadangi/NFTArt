package com.reena.nft.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reena.nft.models.Rating;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
	
	

}
