package pl.serkus.serviceImpl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.serkus.model.Book;
import pl.serkus.model.Category;
import pl.serkus.repository.BookRepository;
import pl.serkus.repository.CategoryRepository;
import pl.serkus.service.LibrarianService;

@Service
@Transactional
public class LibrarianServiceImpl implements LibrarianService{
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(Book book) {
		bookRepository.delete(book);
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
}
