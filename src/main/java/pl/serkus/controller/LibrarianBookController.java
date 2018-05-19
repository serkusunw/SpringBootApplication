package pl.serkus.controller;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.serkus.model.Author;
import pl.serkus.model.Book;
import pl.serkus.model.BorrowedBooks;
import pl.serkus.model.Category;
import pl.serkus.model.PublishingHouse;
import pl.serkus.model.User;
import pl.serkus.repository.BookRepository;
import pl.serkus.service.LibrarianService;
import pl.serkus.service.UserService;
import pl.serkus.validator.LibrarianAuthorValidator;
import pl.serkus.validator.LibrarianCategoryValidator;
import pl.serkus.validator.LibrarianNewBookValidator;
import pl.serkus.validator.LibrarianPublishingHouseValidator;

@Controller
public class LibrarianBookController {

	@Autowired
	UserService userService;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	LibrarianService librarianService;
	
	@RequestMapping(value = "/manageBooks")
	public String showManageBookPage() {
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/category/addform")
	public String showAddCategoryPage(Model model) {
		Category category = new Category();
		
		model.addAttribute("operation", "categoryAdd");
		model.addAttribute("category", category);
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/category/add")
	public String addCategory(Category category, Model model, BindingResult result) {
		new LibrarianCategoryValidator().validate(category, result);
		
		if(!result.hasErrors()) {
			Category cat = librarianService.findByCategory(category);
			if(cat == null) {
				librarianService.addCategory(category);
				model.addAttribute("successAdd", 1);
			}
			else {
				result.rejectValue("name", "error.librarian.name.exists");
			}
		}
		model.addAttribute("operation", "categoryAdd");
		category.setId(0);
		model.addAttribute("category", category);
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/category/deleteform")
	public String showDeleteCategoryPage(Model model) {
		Category category = new Category();
		
		model.addAttribute("operation", "categoryDelete");
		model.addAttribute("category", category);
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/category/delete")
	public String deleteCategory(Category category, Model model, BindingResult result) {
		new LibrarianCategoryValidator().validate(category, result);
		
		if(!result.hasErrors()) {
			Category cat = librarianService.findByCategory(category);
			if(cat != null) {
				librarianService.deleteCategory(cat);
				model.addAttribute("successDelete", 1);
			}
			else {
				result.rejectValue("name", "error.librarian.name.notexists");
			}
		}
		
		model.addAttribute("operation", "categoryDelete");
		category.setId(0);
		model.addAttribute("category", category);
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/category/show/{page}")
	public String showCategories(@PathVariable("page") int page, Model model) {
		int elements = 5;
		Page<Category> pages = librarianService.findAllCategoriesPages(PageRequest.of(page, elements));
		int totalPages = pages.getTotalPages();
		int currentPage = pages.getNumber();
		List<Category> categoryList = pages.getContent();
		
		model.addAttribute("operation", "categoriesShow");
		model.addAttribute("categories", categoryList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		
		return "manageBooks";
	}
	
	
	@RequestMapping(value = "/manageBooks/author/addform")
	public String showAddAuthorPage(Model model) {
		Author author = new Author();
		
		model.addAttribute("operation", "authorAdd");
		model.addAttribute("author", author);
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/author/add")
	public String addAuthor(Author author, Model model, BindingResult result) {
		new LibrarianAuthorValidator().validate(author, result);
		
		if(!result.hasErrors()) {
			Author auth = librarianService.findByAuthor(author);
			if(auth == null) {
				librarianService.addAuthor(author);
				model.addAttribute("successAdd", 1);
			}
			else {
				result.rejectValue("surname", "error.librarian.author.name.exists");
			}
		}
		model.addAttribute("operation", "authorAdd");
		author.setId(0);
		model.addAttribute("author", author);
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/author/deleteform")
	public String showDeleteAuthorPage(Model model) {
		Author author = new Author();
		
		model.addAttribute("operation", "authorDelete");
		model.addAttribute("author", author);
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/author/delete")
	public String deleteAuthor(Author author, Model model, BindingResult result) {
		new LibrarianAuthorValidator().validate(author, result);
		
		if(!result.hasErrors()) {
			Author auth = librarianService.findByAuthor(author);
			if(auth != null) {
				librarianService.deleteAuthor(auth);
				model.addAttribute("successDelete", 1);
			}
			else {
				result.rejectValue("surname", "error.librarian.author.name.notexists");
			}
		}
		
		model.addAttribute("operation", "authorDelete");
		author.setId(0);
		model.addAttribute("author", author);
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/author/show/{page}")
	public String showAuthors(@PathVariable("page") int page, Model model) {
		int elements = 5;
		Page<Author> pages = librarianService.findAllAuthorsPages(PageRequest.of(page, elements));
		int totalPages = pages.getTotalPages();
		int currentPage = pages.getNumber();
		List<Author> authorsList = pages.getContent();
		
		model.addAttribute("operation", "authorsShow");
		model.addAttribute("authors", authorsList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/book/deleteform")
	public String showDeleteBookPage(Model model) {
		
		Book book = new Book();
		Map<Integer, String> authorMap = new HashMap<>();
		
		for(Author author : librarianService.findAllAuthors()) {
			authorMap.put(author.getId(), author.getName() + " " + author.getSurname());
		}
		
		model.addAttribute("operation", "bookDelete");
		model.addAttribute("authorMap", authorMap);
		model.addAttribute("book", book);
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/book/delete")
	public String deleteBook(Book book, Model model, BindingResult result) {
		//new LibrarianAuthorValidator().validate(author, result);
		
		book.setAuthor(librarianService.findAuthorById(book.getAuthorId()));
		
		if(!result.hasErrors()) {
			Book bk = librarianService.findByBook(book);

			if(bk != null) {
				librarianService.deleteBook(bk);
				model.addAttribute("successDelete", 1);
			}
			else {
				result.rejectValue("title", "error.librarian.author.name.notexists");
			}
		}
		
		model.addAttribute("operation", "bookDelete");
		book.setId(0);
		model.addAttribute("book", book);
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/book/addform")
	public String showAddBookPage(Model model) {
		
		Book book = new Book();
		Map<Integer, String> authorMap = new HashMap<>();
		Map<Integer, String> categoryMap = new HashMap<>();
		Map<Integer, String> publishingHousesMap = new HashMap<>();
		
		for(Author author : librarianService.findAllAuthors()) {
			authorMap.put(author.getId(), author.getName() + " " + author.getSurname());
		}
		
		for(Category category : librarianService.findAllCategories()) {
			categoryMap.put(category.getId(), category.getName());
		}
		
		for(PublishingHouse publishingHouse : librarianService.findAllPublishingHouses()) {
			publishingHousesMap.put(publishingHouse.getId(), publishingHouse.getName());
		}
		
		model.addAttribute("operation", "bookAdd");
		model.addAttribute("authorMap", authorMap);
		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("publishingHouseMap", publishingHousesMap);
		model.addAttribute("book", book);
		return "manageBooks";
	}
	
	public String getCurrentUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		return username;
	}
	
	@RequestMapping(value = "/manageBooks/book/add")
	public String addBook(Book book, BindingResult result, Model model) {
		
		final String path = "C:/Users/serku/Documents/workspace-sts-3.9.2.RELEASE/SpringBootApplication/src/main/webapp/WEB-INF/images";
		new LibrarianNewBookValidator().validate(book, result);
		
		book.setAuthor(librarianService.findAuthorById(book.getAuthorId()));
		book.setCategory(librarianService.findCategoryById(book.getCategoryId()));
		book.setPublishingHouse(librarianService.findPublishingHouseById(book.getPublishingHouseId()));
		
		if(!result.hasErrors()) {
			Book bk = librarianService.findByBook(book);
			if(bk == null) {
				String bookName = book.getImage().getOriginalFilename();
				String[] extension = bookName.split("\\.");
				bookName = UUID.randomUUID().toString() + "." + extension[extension.length-1];
				try {
					File transferFile = new File(path + "/" + bookName);
					book.getImage().transferTo(transferFile);

				} catch (Exception e) {
					e.printStackTrace();
				}
				book.setImage_name(bookName);
				book.setRelease_date(Date.valueOf(LocalDate.parse(book.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

				
				
				// testy
				librarianService.saveBook(book);
				
				BorrowedBooks borrowedBooks = new BorrowedBooks();
				User currentUser = userService.findUserByEmail(getCurrentUserName());
				borrowedBooks.setBook(book);
				borrowedBooks.setUser(currentUser);
				currentUser.getBorrowedBooks().add(borrowedBooks);

				librarianService.saveBook(book);
				userService.updateUserData(currentUser);
				
				book.getBorrowedBooks().add(borrowedBooks);
				
				librarianService.saveBook(book);
			
			// testy
				model.addAttribute("successAdd", 1);
			}
			else {
				result.rejectValue("title", "error.librarian.author.name.notexists");
			}
		}
		
		Map<Integer, String> authorMap = new HashMap<>();
		Map<Integer, String> categoryMap = new HashMap<>();
		Map<Integer, String> publishingHousesMap = new HashMap<>();
		
		for(Author author : librarianService.findAllAuthors()) {
			authorMap.put(author.getId(), author.getName() + " " + author.getSurname());
		}
		
		for(Category category : librarianService.findAllCategories()) {
			categoryMap.put(category.getId(), category.getName());
		}
		
		for(PublishingHouse publishingHouse : librarianService.findAllPublishingHouses()) {
			publishingHousesMap.put(publishingHouse.getId(), publishingHouse.getName());
		}
		
		model.addAttribute("authorMap", authorMap);
		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("publishingHouseMap", publishingHousesMap);
		model.addAttribute("operation", "bookAdd");
		book.setId(0);
		model.addAttribute("book", book);
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/publishinghouse/addform")
	public String showAddPublishingHousePage(Model model) {
		
		model.addAttribute("operation", "publishingHouseAdd");
		model.addAttribute("publishingHouse", new PublishingHouse());
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/publishinghouse/add")
	public String addPublishingHouse(PublishingHouse publishingHouse, Model model, BindingResult result) {
		new LibrarianPublishingHouseValidator().validate(publishingHouse, result);
		
		if(!result.hasErrors()) {
			PublishingHouse pHouse = librarianService.findByPublishingHouse(publishingHouse);
			if(pHouse == null) {
				librarianService.addPublishingHouse(publishingHouse);
				model.addAttribute("successAdd", 1);
			}
			else {
				result.rejectValue("name", "error.librarian.publishingHouse.exists");
			}
		}

		model.addAttribute("operation", "publishingHouseAdd");
		publishingHouse.setId(0);
		model.addAttribute("publishingHouse", publishingHouse);
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/publishinghouse/deleteform")
	public String showDeletePublishingHousePage(Model model) {
		
		model.addAttribute("operation", "publishingHouseDelete");
		model.addAttribute("publishingHouse", new PublishingHouse());
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/publishinghouse/delete")
	public String deletePublishingHouse(PublishingHouse publishingHouse, Model model, BindingResult result) {
		
		new LibrarianPublishingHouseValidator().validate(publishingHouse, result);
		
		if(!result.hasErrors()) {
			PublishingHouse pHouse = librarianService.findByPublishingHouse(publishingHouse);
			if(pHouse != null) {
				librarianService.deletePublishingHouse(pHouse);
				model.addAttribute("successDelete", 1);
			}
			else {
				result.rejectValue("name", "error.librarian.publishingHouse.notexists");
			}
		}
		model.addAttribute("operation", "publishingHouseDelete");
		publishingHouse.setId(0);
		model.addAttribute("publishingHouse", publishingHouse);
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/publishinghouse/show/{page}")
	public String showPublishingHouses(@PathVariable("page") int page, Model model) {
		int elements = 5;
		Page<PublishingHouse> pages = librarianService.findAllPublishingHousesPages(PageRequest.of(page, elements));
		int totalPages = pages.getTotalPages();
		int currentPage = pages.getNumber();
		List<PublishingHouse> publishingHousesList = pages.getContent();
		
		model.addAttribute("operation", "publishingHousesShow");
		model.addAttribute("publishingHouses", publishingHousesList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		
		return "manageBooks";
	}
}
