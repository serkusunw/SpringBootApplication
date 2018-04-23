package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.serkus.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	public Book findByTitle(String title);
}