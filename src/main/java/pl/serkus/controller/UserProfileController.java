package pl.serkus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.serkus.model.User;
import pl.serkus.service.UserService;

@Controller
public class UserProfileController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/profile")
	@Secured(value = {"ROLE_USER"})
	public String getProfilePage(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		User user = userService.findUserByEmail(username);

		model.addAttribute("user", user);

		return "/profile";
	}
}
