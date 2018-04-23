package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.serkus.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
