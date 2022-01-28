package com.reena.nft.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reena.nft.models.User;
import com.reena.nft.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	// register user and hash their password
	public User registerUser(User user) {
		String hashedPass=BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedPass);
		return userRepository.save(user);
	}
    
    // find user by email
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
    // find user by id
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
   // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

}
