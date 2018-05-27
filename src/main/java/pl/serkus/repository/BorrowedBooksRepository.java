package pl.serkus.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.serkus.model.BorrowedBooks;

@Repository("BorrowedBooksRepository")
public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Integer>{

	@Query(value = "SELECT * FROM borrowed_books JOIN book ON book.book_id = borrowed_books.book_id WHERE borrowed_books.user_id = ?1", nativeQuery = true)
	public Page<BorrowedBooks> findBorrowedBooks(int user_id, Pageable pageable);
	
	@Query(value = "SELECT * FROM borrowed_books JOIN book ON book.book_id = borrowed_books.book_id WHERE borrowed_books.user_id = ?1", nativeQuery = true)
	public List<BorrowedBooks> findUserBorrowedBooks(int user_id);
	
	@Query(value = "SELECT * FROM borrowed_books WHERE book_id = ?1 AND user_id = ?2", nativeQuery = true)
	public BorrowedBooks findByUserBook(int book_id, int user_id);
}
