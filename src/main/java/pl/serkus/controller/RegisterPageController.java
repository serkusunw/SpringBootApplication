package pl.serkus.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.serkus.model.User;
import pl.serkus.service.UserService;
import pl.serkus.validator.UserRegistrationValidator;

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
	public String registerUserAction(User user, BindingResult result, Model model) {
		String returnPage = null;
		
		User u = userService.findUserByEmail(user.getEmail());
		
		UserRegistrationValidator v = new UserRegistrationValidator(u);
		v.validate(user, result);
		
		if (result.hasErrors()) {
			returnPage = "/register";
		} else {
			user.setRegisterDate(new Date(Calendar.getInstance().getTimeInMillis()));
			user.setAge(Date.valueOf(LocalDate.parse(user.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
			userService.saveUser(user);
			model.addAttribute("registrationSuccess", 1);
			returnPage = "/login";
		}
		return returnPage;
	}
}
