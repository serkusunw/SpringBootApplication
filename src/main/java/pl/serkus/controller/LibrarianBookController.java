package pl.serkus.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.serkus.model.Category;
import pl.serkus.service.LibrarianService;
import pl.serkus.service.UserService;
import pl.serkus.validator.LibrarianCategoryValidator;

@Controller
public class LibrarianBookController {

	@Autowired
	UserService userService;
	
	@Autowired
	LibrarianService librarianService;
	
	@RequestMapping(value = "/manageBooks")
	public String showManageBookPage() {
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/category/addform")
	public String showAddCategoryPage(Model model) {
		Category category = new Category();
		
		model.addAttribute("operation", "categoryAdd");
		model.addAttribute("category", category);
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/category/deleteform")
	public String showDeleteCategoryPage(Model model) {
		Category category = new Category();
		
		model.addAttribute("operation", "categoryDelete");
		model.addAttribute("category", category);
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/category/add")
	public String addCategory(Category category, Model model, BindingResult result) {
		new LibrarianCategoryValidator().validate(category, result);
		
		if(!result.hasErrors()) {
			Category cat = librarianService.findByCategory(category);
			if(cat == null) {
				librarianService.addCategory(category);
				model.addAttribute("successAdd", 1);
			}
			else {
				result.rejectValue("name", "error.librarian.name.exists");
			}
		}
		model.addAttribute("operation", "categoryAdd");
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/category/delete")
	public String deleteCategory(Category category, Model model, BindingResult result) {
		new LibrarianCategoryValidator().validate(category, result);
		
		if(!result.hasErrors()) {
			Category cat = librarianService.findByCategory(category);
			if(cat != null) {
				librarianService.deleteCategory(cat);
				model.addAttribute("successDelete", 1);
			}
			else {
				result.rejectValue("name", "error.librarian.name.notexists");
			}
		}
		
		model.addAttribute("operation", "categoryDelete");
		
		return "manageBooks";
	}
	
	@RequestMapping(value = "/manageBooks/category/show/{page}")
	public String showCategories(@PathVariable("page") int page, Model model) {
		int elements = 5;
		Page<Category> pages = librarianService.findAllCategoriesPages(PageRequest.of(page, elements));
		int totalPages = pages.getTotalPages();
		int currentPage = pages.getNumber();
		List<Category> categoryList = pages.getContent();
		
		model.addAttribute("operation", "categoriesShow");
		model.addAttribute("categories", categoryList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);
		
		return "manageBooks";
	}
}
