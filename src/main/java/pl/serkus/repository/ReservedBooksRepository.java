package pl.serkus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.serkus.model.ReservedBooks;

@Repository("ReservedBooksRepository")
public interface ReservedBooksRepository extends JpaRepository<ReservedBooks, Integer>{

	@Query(value = "SELECT COUNT(user_id) FROM reserved_books WHERE user_id = ?1 AND book_id = ?2", nativeQuery = true)
	public int findByUserAndBook(Integer user_id, Integer book_id);
	
	@Query(value = "SELECT COUNT(user_id) FROM reserved_books WHERE book_id = ?1", nativeQuery = true)
	public int findBookCount(Integer book_id);
	
	@Query(value = "SELECT * FROM reserved_books JOIN book ON book.book_id = reserved_books.book_id WHERE reserved_books.user_id = ?1", nativeQuery = true)
	public Page<ReservedBooks> findReservedBooks(int user_id, Pageable pageable);
}
