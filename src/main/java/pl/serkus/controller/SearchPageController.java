package pl.serkus.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.serkus.model.Book;
import pl.serkus.service.SearchService;

@Controller
public class SearchPageController {

	@Autowired
	SearchService searchService;
	
	@RequestMapping(value = "/library/search")
	public String searchBooks(@RequestParam("searchText") String titleToSearch, Model model) {

		List<Book> foundBooks = searchService.searchBooks(titleToSearch);
		
		if(!foundBooks.isEmpty()) {
			model.addAttribute("books", foundBooks);
			model.addAttribute("success", 1);
			model.addAttribute("operation", "showFoundBooks");
		}
		else {
			model.addAttribute("success", 0);
		}
		
		return "search";
	}
}