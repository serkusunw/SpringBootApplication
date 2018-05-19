package pl.serkus.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.serkus.model.Book;
import pl.serkus.model.Category;
import pl.serkus.service.LibrarianService;
import pl.serkus.service.UserService;

@Controller
public class BooksPageController {

	@Autowired
	LibrarianService librarianService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/showBooks")
	public String showBooksPage(Model model) {
		List<Category> categories = librarianService.findAllCategories();
		model.addAttribute("categories", categories);
		
		return "showBooks";
	}
	
	@RequestMapping("/showBooks/{category_id}/{page}")
	public String showBooksPage(@PathVariable("category_id") int category_id, @PathVariable("page") int page, Model model) {
		List<Category> categories = librarianService.findAllCategories();
		final int resultsByPage = 4;
		Page<Book> pages = librarianService.findAllBooksByCategory(category_id, PageRequest.of(page, resultsByPage));
		
		int totalPages = pages.getTotalPages();
		int currentPage = pages.getNumber();
		List<Book> books = pages.getContent();
		
		model.addAttribute("operation", "showBooks");
		model.addAttribute("books", books);
		model.addAttribute("currentcategory", category_id);
		model.addAttribute("categories", categories);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		
		return "showBooks";
	}
	
	@RequestMapping("/showBooks/{category_id}/{page}/{book}")
	public String showBookPage(@PathVariable("category_id") int category_id, @PathVariable("page") int page, @PathVariable("book") int book_id, Model model) {
		List<Category> categories = librarianService.findAllCategories();

		Book book = librarianService.findBookById(book_id);
		
		model.addAttribute("operation", "showBook");
		model.addAttribute("book", book);
		model.addAttribute("categories", categories);
		model.addAttribute("currentPage", page);
		model.addAttribute("currentcategory", category_id);
		
		return "showBooks";
	}
	
	@RequestMapping("/showBooks/{category_id}/{page}/{book}/reservation")
	public String reserveBook(@PathVariable("category_id") int category_id, @PathVariable("page") int page, @PathVariable("book") int book_id, Model model) {
		List<Category> categories = librarianService.findAllCategories();

		Book book = librarianService.findBookById(book_id);
		
		librarianService.reserveBook(book);
		
		model.addAttribute("operation", "showBook");
		model.addAttribute("book", book);
		model.addAttribute("categories", categories);
		model.addAttribute("currentPage", page);
		model.addAttribute("currentcategory", category_id);
		
		return "showBooks";
	}
	
}
