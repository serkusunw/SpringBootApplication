package pl.serkus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginPageController {
	
	@RequestMapping(value = "/login")
	public String showLoginForm() {
		return "login";
	}
	
	
}