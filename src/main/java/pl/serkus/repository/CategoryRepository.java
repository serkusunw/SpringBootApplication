package pl.serkus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.serkus.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	public Category findByName(String name);
}
