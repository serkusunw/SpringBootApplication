package pl.serkus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.serkus.model.*;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByEmail(String email);
	public List<User> findByNameAndSurname(String name, String surname);
}
