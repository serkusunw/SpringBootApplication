package pl.serkus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.serkus.model.Book;
import pl.serkus.service.LibrarianService;

@Controller
public class MainPageController {
	
	@Autowired
	LibrarianService librarianService;
	
	@RequestMapping("/")
	public String showMainPage(Model model) {
		List<Book> books = new ArrayList<>();
		
		for(int i = 0; i < 3; i++)
			books.add(librarianService.getRandomBook());
		
		model.addAttribute("books", books);
		
		return "index";
	}
}
