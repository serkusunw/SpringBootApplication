package pl.serkus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.serkus.model.Book;

@Repository("BookRepository")
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query(value = "SELECT * FROM Book JOIN Author ON Book.author_id = Author.author_id WHERE Book.title = ?1 AND Author.name = ?2 AND Author.surname = ?3", nativeQuery = true)
	public Book findByTitleAndAuthor(String title, String authorName, String authorSurname);
	
	public Book findByTitle(String title);
	
	@Query(value = "SELECT * FROM Book JOIN Category ON Category.category_id = Book.category_id WHERE Category.category_id = ?1", nativeQuery = true)
	Page<Book> findByCategoryId(int id, Pageable pageable);
}