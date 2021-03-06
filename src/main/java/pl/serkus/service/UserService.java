package pl.serkus.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.serkus.model.User;

public interface UserService {
	
	public void saveUser(User user);
	
	public void saveUserEdited(User user);
	
	public List<User> findAll();
	
	public Page<User> findAllPages(Pageable pageable);
	
	public User findUserByEmail(String email);
	
	public List<User> findUserByNameAndSurname(String name, String surname);
	
	public User findUserById(int id);
	
	public void updateUser(String role, User user);
	
	public void updateUser(User user);
	
	public void updateUserData(User user);
	
	public void deleteUser(User user);
}
