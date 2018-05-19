package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.serkus.model.BorrowedBooks;

@Repository("BorrowedBooksRepository")
public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Integer>{

}
