package pl.serkus.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.serkus.model.Category;

public class LibrarianCategoryValidator implements Validator{

	@Override
	public boolean supports(Class<?> c) {
		return Category.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors err) {
		Category category = (Category)obj;
		
		if(category.getName().length() == 0) {
			err.rejectValue("name", "error.emptyField");
		}
		else {
			if(category.getName().length() > 30) {
				err.rejectValue("name", "error.librarian.limit.30");
			}
		}
	}

}
