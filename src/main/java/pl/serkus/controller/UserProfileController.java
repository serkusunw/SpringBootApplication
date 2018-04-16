package pl.serkus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.serkus.model.User;
import pl.serkus.service.UserService;
import pl.serkus.validator.UserEditValidator;

@Controller
public class UserProfileController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/profile")
	@Secured(value = {"ROLE_USER"})
	public String getProfilePage(Model model) {
		
		String username = getCurrentUserName();

		User user = userService.findUserByEmail(username);

		model.addAttribute("user", user);

		return "/profile";
	}
	
	@RequestMapping("/profile/edit")
	@Secured(value = {"ROLE_USER"})
	public String getEditProfilePage(Model model) {
		
		String username = getCurrentUserName();
		User user = userService.findUserByEmail(username);
		
		user.setPassword(null);
		model.addAttribute("user", user);

		return "profileEdit";
	}
	
	@RequestMapping("/user/update")
	@Secured(value = {"ROLE_USER"})
	public String updateUserProfile(Model model, User user, BindingResult result) {
		
		String returnPage = null;

		new UserEditValidator().validate(user, result);
		
		if(user.getPassword().length() == 0  && user.getPasswordCheck().length() == 0)
		{		
			String username = getCurrentUserName();
			User u = userService.findUserByEmail(username);
			user.setPassword(u.getPassword());
		}
		
		if(result.hasErrors()) {
			user.setPassword(user.getPassword());
			model.addAttribute("user", user);
			
			returnPage = "profileEdit";
		}
		else {
			userService.saveUserEdited(user);
			
			returnPage = "profile";
		}

		return returnPage;
	}
	
	public String getCurrentUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		return username;
	}
}
