package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.serkus.model.Author;

@Repository("AuthorRepository")
public interface AuthorRepository extends JpaRepository<Author, Integer>{
	public Author findByNameAndSurname(String name, String surname);
}
