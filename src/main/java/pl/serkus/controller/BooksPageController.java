package pl.serkus.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.serkus.model.Book;
import pl.serkus.model.BorrowedBooks;
import pl.serkus.model.Category;
import pl.serkus.model.ReservedBooks;
import pl.serkus.repository.BookRepository;
import pl.serkus.service.LibrarianService;
import pl.serkus.service.UserService;

@Controller
public class BooksPageController {

	@Autowired
	LibrarianService librarianService;
	
	@Autowired
	BookRepository bookRepository;
	
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
	public String reserveBook(@PathVariable("category_id") int category_id, @PathVariable("page") int page, @PathVariable("book") int book_id, Model model, RedirectAttributes redirectAttributes) {
		Book book = librarianService.findBookById(book_id);
		int  status = librarianService.reserveBook(book);
		
		redirectAttributes.addFlashAttribute("reservationStatus", status);
		
		return "redirect:/showBooks/"+category_id+"/"+page;
	}
	
	
	@RequestMapping("/yourBooks/{operation}/{page}")
	public String showYourBooksPage(@PathVariable("operation") String operation, @PathVariable("page") int page, Model model) {
		String op = "showReservedBooks";
		int resultsByPage = 3;
		
		if(operation.equals("reserved"))
		{
			Page<ReservedBooks> pages = librarianService.findReservedUserBooks(PageRequest.of(page, resultsByPage));
			int totalPages = pages.getTotalPages();
			int currentPage = pages.getNumber();
			List<ReservedBooks> books = pages.getContent();
			
			List<Book> reservedBooks = new ArrayList<>();
			
			for(ReservedBooks book : books) {
				Book b = new Book();
				b = book.getBook();
				
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(book.getReservationDate().getTime());
				cal.add(Calendar.DAY_OF_MONTH, 3);
				
				b.setReservation_date(new Date(cal.getTimeInMillis()));
				reservedBooks.add(b);
			}
			
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("books", reservedBooks);
		}
		else if(operation.equals("borrowed")) {
			op = "showBorrowedBooks";
			
			Page<BorrowedBooks> pages = librarianService.findBorrowedUserBooks(PageRequest.of(page, resultsByPage));
			int totalPages = pages.getTotalPages();
			int currentPage = pages.getNumber();
			List<BorrowedBooks> books = pages.getContent();
			
			List<Book> borrowedBooks = new ArrayList<>();
			
			for(BorrowedBooks book : books) {
				Book b = new Book();
				b = book.getBook();
				
				b.setRentalDate(book.getRentalDate());
				b.setReturnDate(book.getReturnDate());
				borrowedBooks.add(b);
			}
			
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("books", borrowedBooks);
		}
		
		model.addAttribute("operation", op);
		
		return "yourBooks";
	}
	
}
