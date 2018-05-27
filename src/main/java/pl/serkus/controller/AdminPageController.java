package pl.serkus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.serkus.model.User;
import pl.serkus.service.UserService;

@Controller
public class AdminPageController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/admin")
	public String showAdminPage() {
		return "admin/admin";
	}
	
	@RequestMapping(value = "/admin/users")
	public String getAllUsers() {
		return "redirect:users/0";
	}
	
	@RequestMapping(value = "admin/users/{page}")
	public String showUsersPageable(@PathVariable("page") int page, Model model) {
		
		int elements = 5;
		Page<User> pages = userService.findAllPages(PageRequest.of(page, elements));
		int totalPages = pages.getTotalPages();
		int currentPage = pages.getNumber();
		List<User> userList = pages.getContent();
		
		for(User user : userList) {
			int roleNumber = user.getRoles().iterator().next().getId();
			user.setRoleId(roleNumber);
		}
		
		model.addAttribute("userList", userList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		
		return "/admin/users";
	}
	
	@RequestMapping(value = "/admin/edit/{id}")
	public String getUserToEdit(@PathVariable("id") String id, Model model) {
		
		User user = new User();
		user.setId(Integer.parseInt(id));
		int userId = Integer.parseInt(id);
		user = userService.findUserById(userId);
		
		Map<Integer, String> roleMap = new HashMap<>();
		roleMap.put(1, "Użytkownik");
		roleMap.put(2, "Administrator");
		roleMap.put(3, "Bibliotekarz");
		
		int role = user.getRoles().iterator().next().getId();
		
		user.setRoleId(role);
		
		model.addAttribute("roleMap", roleMap);
		model.addAttribute("user", user);
		
		return "/admin/useredit";
	}
	
	@RequestMapping(value = "/admin/update")
	public String updateUser(Model model, @Valid User user, BindingResult result) {
		
			String returnPage = null;
			String role = null;
			
			User u = userService.findUserById(user.getId());
			
			u.setName(user.getName());
			u.setSurname(user.getSurname());
			u.setDate(user.getDate());
			u.setEmail(user.getEmail());
			
			int role_id = user.getRoleId();
			
			if(role_id == 1) {
				role = "ROLE_USER";
			}
			else if(role_id == 2){
				role = "ROLE_ADMIN";
			}
			else if(role_id == 3){
				role = "ROLE_LIBRARIAN";
			}
			
			if(result.hasErrors()) {
				Map<Integer, String> roleMap = new HashMap<>();
				roleMap.put(1, "Użytkownik");
				roleMap.put(2, "Administrator");
				roleMap.put(3, "Bibliotekarz");
				model.addAttribute("roleMap", roleMap);
				
				returnPage = "/admin/useredit";
			}
			else {
				userService.updateUser(role, u);
				
				returnPage = "redirect:/admin/users";
			}
			
			return returnPage;
	}
	
	@RequestMapping(value = "/admin/delete/{id}")
	public String getUserToDelete(@PathVariable("id") String id) {
		
		User user = new User();
		
		int userId = Integer.parseInt(id);
		user = userService.findUserById(userId);
		
		userService.deleteUser(user);
		
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = "/admin/lockaccount/{id}")
	public String getUserLock(@PathVariable("id") String id) {
		
		User user = relockUser(id, false);
		
		userService.updateUser(user.getRoles().iterator().next().getRole(), user);
		
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = "/admin/unlockaccount/{id}")
	public String getUserToUnlock(@PathVariable("id") String id) {
		
		User user = relockUser(id, true);
		
		userService.updateUser(user.getRoles().iterator().next().getRole(), user);
		
		return "redirect:/admin/users";
	}
	
	public User relockUser(String id, boolean active) {
		User user = new User();
		int userId = Integer.parseInt(id);
		user = userService.findUserById(userId);
		
		user.setActive(active);
		
		return user;
	}
	
	public List<User> getUserList(){
		List<User> userList = userService.findAll();
		
		for(User user : userList) {
			
			int roleNumber = user.getRoles().iterator().next().getId();
			user.setRoleId(roleNumber);
		}
		return userList;
	}
}