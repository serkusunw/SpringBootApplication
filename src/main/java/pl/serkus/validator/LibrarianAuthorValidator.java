package pl.serkus.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.serkus.model.Author;

public class LibrarianAuthorValidator implements Validator{

	@Override
	public boolean supports(Class<?> c) {
		return Author.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors err) {
		Author category = (Author)obj;
		
		if(category.getName().length() == 0) {
			err.rejectValue("name", "error.emptyField");
		}
		else {
			if(category.getName().length() > 30) {
				err.rejectValue("name", "error.librarian.limit.30");
			}
		}
		
		if(category.getSurname().length() == 0) {
			err.rejectValue("surname", "error.emptyField");
		}
		else {
			if(category.getSurname().length() > 30) {
				err.rejectValue("surname", "error.librarian.limit.30");
			}
		}
	}

}
