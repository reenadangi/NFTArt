package com.reena.nft.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reena.nft.models.Art;
import com.reena.nft.models.Rating;
import com.reena.nft.models.User;
import com.reena.nft.services.ArtService;
import com.reena.nft.services.UserService;
import com.reena.nft.validator.UserValidator;

@Controller
public class HomeController {
	@Autowired
	private  UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired 
	private ArtService artService;
	
	
	
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "index.jsp";
	}
	// Register a User
	@PostMapping("/registration")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult results, HttpSession session) {
		userValidator.validate(user, results);
		if(results.hasErrors()) {
			return "index.jsp";
		}else {
			User newUser=userService.registerUser(user);
			session.setAttribute("userId", newUser.getId());
			return "redirect:/arts";
		}
		
	}
	// Login
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirectAttributes) {
        // if the user is authenticated, save their user id in session
		if(userService.authenticateUser(email, password)) {
			User user=userService.findByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/arts";
		}else {
			redirectAttributes.addFlashAttribute("error","Invalid user/pass");
			return "redirect:/";
		}
	}
//	Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
// **********************NFT Art Routes************************* 
	// Collection 
		@GetMapping("/arts")
		public String arts(Model model,HttpSession session) {
			if(session.getAttribute("userId")!=null) {
				User user=userService.findById((Long)session.getAttribute("userId"));
				model.addAttribute("user",user);
				model.addAttribute("arts", artService.allArts());
				return "arts.jsp";
			}else {
				return "redirect:/";
			}
			
		}
		
//		New Art work 
		@GetMapping("/arts/new")
		public String newArt(@ModelAttribute("newArt") Art art, HttpSession session) {
			if(session.getAttribute("userId")!=null) {	
				return "new.jsp";
			}
			else {
				return "redirect:/";
			}
			
		}
		
//		Create Art Work 
		@PostMapping("/arts/create")
		public String createArt(@Valid @ModelAttribute("newArt") Art art, BindingResult results) {
			if(results.hasErrors()) {
				return "new.jsp";
			}else {
				artService.createArt(art);
				return "redirect:/arts";
			}
		}

//Get art details 
		@GetMapping("/arts/{id}")
		public String artDetails(@PathVariable("id") Long id, Model model, @ModelAttribute("newRating") Rating rating) {
			model.addAttribute("art", artService.artDetails(id));
			return "art.jsp";
		}
		
		@PostMapping("/art/addRating")
		public String addRating(@ModelAttribute("newRating") Rating rating) {
			artService.AddRating(rating);
			return "redirect:/arts";
			
		}
		@GetMapping("/arts/{id}/delete")
		public String deleteArt(@PathVariable("id") Long id) {
			artService.deleteArt(id);
			return "redirect:/arts";
		}
}
