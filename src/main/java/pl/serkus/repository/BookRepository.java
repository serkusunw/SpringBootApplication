package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.serkus.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query(value = "SELECT * FROM Book JOIN Author ON Book.author_id = Author.author_id WHERE Book.title = ?1 AND Author.name = ?2 AND Author.surname = ?3", nativeQuery = true)
	public Book findByTitleAndAuthor(String title, String authorName, String authorSurname);
	
	public Book findByTitle(String title);
}