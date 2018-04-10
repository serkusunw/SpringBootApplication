package pl.serkus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
	public String getAllUsers(Model model) {

		List<User> userList = userService.findAll();
		
		for(User user : userList) {
			
			int roleNumber = user.getRoles().iterator().next().getId();
			user.setRoleId(roleNumber);
		}
		
		model.addAttribute("userList", userList);
		
		return "admin/users";
	}
	
	@RequestMapping(value = "/admin/edit/{id}")
	public String getUserToEdit(@PathVariable("id") String id, Model model) {
		
		User user = new User();
		int userId = Integer.parseInt(id);
		user = userService.findUserById(userId);
		
		Map<Integer, String> roleMap = new HashMap<>();
		roleMap.put(1, "Użytkownik");
		roleMap.put(2, "Administrator");
		
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
					
			if(user.getRoleId() == 1) {
				role = "ROLE_USER";
			}
			else if(user.getRoleId()== 2){
				role = "ROLE_ADMIN";
			}
			
			if(result.hasErrors()) {
				Map<Integer, String> roleMap = new HashMap<>();
				roleMap.put(1, "Użytkownik");
				roleMap.put(2, "Administrator");
				model.addAttribute("roleMap", roleMap);
				
				returnPage = "admin/useredit";
			}
			else {
				userService.updateUser(role, user);
				
				List<User> userList = userService.findAll();
				
				for(User user1 : userList) {
					
					int roleNumber = user1.getRoles().iterator().next().getId();
					user1.setRoleId(roleNumber);
				}
				
				model.addAttribute("userList", userList);
				returnPage = "admin/users";
			}
			
			return returnPage;
	}
}