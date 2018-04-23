package pl.serkus.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.serkus.model.Book;
import pl.serkus.model.Category;

public interface LibrarianService {
	
	public void saveBook(Book book);
	
	public Book findBookByTitle(String title);
	
	public void deleteBook(Book book);
	
	public Category findByCategory(Category category);
	
	public void addCategory(Category category);
	
	public void deleteCategory(Category category);
	
	public List<Category> findAllCategories();
	
	public Page<Category> findAllCategoriesPages(Pageable pageable);
}
