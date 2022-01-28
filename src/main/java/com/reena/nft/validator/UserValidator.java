package com.reena.nft.validator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.reena.nft.models.User;
import com.reena.nft.repositories.UserRepository;

@Component
public class UserValidator implements Validator{
	
	@Autowired
	private UserRepository uRepo;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
		
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		User user=(User) target;
		if(!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("password","Match","Password Does not Match");
		}
		
		 if(this.uRepo.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email","unique","Email address already taken");
			
		}
		
		
		
		
		
	}
	
	
	

}
