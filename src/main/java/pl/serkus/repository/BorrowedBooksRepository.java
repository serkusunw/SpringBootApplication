package pl.serkus.repository;

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
}
