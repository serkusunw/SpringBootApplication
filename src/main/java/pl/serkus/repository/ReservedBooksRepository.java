package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.serkus.model.ReservedBooks;

@Repository("ReservedBooksRepository")
public interface ReservedBooksRepository extends JpaRepository<ReservedBooks, Integer>{

}
