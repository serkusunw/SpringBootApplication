package pl.serkus.serviceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.serkus.model.Author;
import pl.serkus.model.Book;
import pl.serkus.model.BorrowedBooks;
import pl.serkus.model.Category;
import pl.serkus.model.PublishingHouse;
import pl.serkus.model.ReservedBooks;
import pl.serkus.model.User;
import pl.serkus.repository.AuthorRepository;
import pl.serkus.repository.BookRepository;
import pl.serkus.repository.BorrowedBooksRepository;
import pl.serkus.repository.CategoryRepository;
import pl.serkus.repository.PublishingHouseRepository;
import pl.serkus.repository.ReservedBooksRepository;
import pl.serkus.service.LibrarianService;
import pl.serkus.service.UserService;

@Service
@Transactional
public class LibrarianServiceImpl implements LibrarianService{
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	PublishingHouseRepository publishingHouseRepository;

	@Autowired
	ReservedBooksRepository reservedBooksRepository;
	
	@Autowired
	BorrowedBooksRepository borrowedBooksRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(Book book) {
		bookRepository.deleteById(book.getId());
	}
	
	@Override
	public int reserveBook(Book book) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		ReservedBooks reservation = new ReservedBooks();
		reservation.setBook(book);
		reservation.setUser(user);
		reservation.setReservationDate(Date.valueOf(LocalDate.now()));

		int result = reservedBooksRepository.findByUserAndBook(user.getId(), book.getId());
		
		if(result == 0) {
			if(book.getCount() > 0) {
				reservedBooksRepository.save(reservation);
				book.setCount(book.getCount() - 1);
				bookRepository.save(book);
				return 1;
			}
			else
				return -1;
		}
		else
			return 0;
	}
	
	@Override
	public Page<ReservedBooks> findReservedUserBooks(Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		return reservedBooksRepository.findReservedBooks(user.getId(), pageable);
	}
	
	@Override
	public Page<BorrowedBooks> findBorrowedUserBooks(Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		return borrowedBooksRepository.findBorrowedBooks(user.getId(), pageable);
	}

	@Override
	public Book findBookByTitle(String title) {
		Book book = bookRepository.findByTitle(title);
		return book;
	}

	@Override
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Category category) {
		categoryRepository.deleteById(category.getId());
	}

	@Override
	public Category findByCategory(Category category) {
		Category finded = categoryRepository.findByName(category.getName());
		return finded;
	}

	@Override
	public List<Category> findAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}
	
	@Override
	public Page<Category> findAllCategoriesPages(Pageable pageable) {
		Page<Category> pages = categoryRepository.findAll(pageable);
		
		return pages;
	}

	@Override
	public Page<Author> findAllAuthorsPages(Pageable pageable) {
		Page<Author> pages = authorRepository.findAll(pageable);
		
		return pages;
	}

	@Override
	public Author findByAuthor(Author author) {
		return authorRepository.findByNameAndSurname(author.getName(), author.getSurname());
	}

	@Override
	public void addAuthor(Author author) {
		authorRepository.save(author);
	}

	@Override
	public void deleteAuthor(Author author) {
		authorRepository.delete(author);
	}

	@Override
	public Book findByBook(Book book) {
		return bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor().getName(), book.getAuthor().getSurname());
	}

	@Override
	public void addBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public List<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public PublishingHouse findByPublishingHouse(PublishingHouse publishingHouse) {
		return publishingHouseRepository.findByName(publishingHouse.getName());
	}

	@Override
	public void addPublishingHouse(PublishingHouse publishingHouse) {
		publishingHouseRepository.save(publishingHouse);
	}

	@Override
	public void deletePublishingHouse(PublishingHouse publishingHouse) {
		publishingHouseRepository.deleteById(publishingHouse.getId());
	}

	@Override
	public Page<PublishingHouse> findAllPublishingHousesPages(Pageable pageable) {
		
		return publishingHouseRepository.findAll(pageable);
	}

	@Override
	public List<PublishingHouse> findAllPublishingHouses() {
		return publishingHouseRepository.findAll();
	}

	@Override
	public Category findCategoryById(int id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public Author findAuthorById(int id) {
		return authorRepository.findById(id).get();
	}

	@Override
	public PublishingHouse findPublishingHouseById(int id) {
		return publishingHouseRepository.findById(id).get();
	}

	@Override
	public Book getRandomBook() {
		Random generator = new Random();
		List<Book> books = bookRepository.findAll();
		int size = books.size();
		if(books.size() > 0){
			if(books.size() == 0)
				size = 1;
			Book book = books.get(generator.nextInt(size));
			return book;
		}
		else
			return null;
	}

	@Override
	public Page<Book> findAllBooksByCategory(int id, Pageable pageable) {
		
		return bookRepository.findByCategoryId(id, pageable);
	}

	@Override
	public Book findBookById(int id) {
		return bookRepository.findById(id).get();
	}
}
