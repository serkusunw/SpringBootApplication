package pl.serkus.controller;

import java.sql.Date;
import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.serkus.model.User;
import pl.serkus.service.UserService;

@Controller
public class RegisterPageController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/register")
	public String showRegisterForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "register";
	}
	
	@RequestMapping(value = "/adduser")
	public String registerUserAction(@Valid User user, BindingResult result, Model model) {
		String returnPage = null;

		User receivedUser = userService.findUserByEmail(user.getEmail());
		if (receivedUser != null) {
			result.rejectValue("email", "error.emailExists");
		}
		if(!user.getPassword().equals(user.getPasswordCheck()) || user.getPasswordCheck().isEmpty()) {
			result.rejectValue("passwordCheck", "error.wrongPassword");
		}
		if (result.hasErrors()) {
			returnPage = "register";
		} else {
			user.setRegisterDate(new Date(Calendar.getInstance().getTimeInMillis()));
			userService.saveUser(user);
			model.addAttribute("message", "Rejestracja przebiegła pomyślnie. Możesz się zalogować");
			model.addAttribute("user", new User());
			returnPage = "register";
		}
		return returnPage;
	}
}
