package pl.serkus.serviceImpl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.serkus.model.Author;
import pl.serkus.model.Book;
import pl.serkus.model.Category;
import pl.serkus.model.PublishingHouse;
import pl.serkus.repository.AuthorRepository;
import pl.serkus.repository.BookRepository;
import pl.serkus.repository.CategoryRepository;
import pl.serkus.repository.PublishingHouseRepository;
import pl.serkus.service.LibrarianService;

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
	
	@Override
	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(Book book) {
		bookRepository.deleteById(book.getId());
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
}
