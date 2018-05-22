package pl.serkus.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.serkus.model.Author;
import pl.serkus.model.Book;
import pl.serkus.model.BorrowedBooks;
import pl.serkus.model.Category;
import pl.serkus.model.PublishingHouse;
import pl.serkus.model.ReservedBooks;

public interface LibrarianService {
	
	public void saveBook(Book book);
	
	public int reserveBook(Book book);
	
	public Page<ReservedBooks> findReservedUserBooks(Pageable pageable);
	
	public Page<BorrowedBooks> findBorrowedUserBooks(Pageable pageable);
	
	public Book findBookByTitle(String title);
	
	public Book findBookById(int id);
	
	public Page<Book> findAllBooksByCategory(int id, Pageable pageable);
	
	public Book getRandomBook();
	
	public void deleteBook(Book book);
	
	public Category findByCategory(Category category);
	
	public Category findCategoryById(int id);
	
	public void addCategory(Category category);
	
	public void deleteCategory(Category category);
	
	public List<Category> findAllCategories();
	
	public Page<Category> findAllCategoriesPages(Pageable pageable);
	
	public Page<Author> findAllAuthorsPages(Pageable pageable);
	
	public Author findByAuthor(Author author);
	
	public Author findAuthorById(int id);
	
	public List<Author> findAllAuthors();
	
	public void addAuthor(Author author);
	
	public void deleteAuthor(Author author);
	
	public Book findByBook(Book book);
	
	public void addBook(Book book);
	
	public PublishingHouse findByPublishingHouse(PublishingHouse publishingHouse);
	
	public PublishingHouse findPublishingHouseById(int id);
	
	public void addPublishingHouse(PublishingHouse publishingHouse);
	
	public void deletePublishingHouse(PublishingHouse publishingHouse);
	
	public List<PublishingHouse> findAllPublishingHouses();
	
	public Page<PublishingHouse> findAllPublishingHousesPages(Pageable pageable);
}
