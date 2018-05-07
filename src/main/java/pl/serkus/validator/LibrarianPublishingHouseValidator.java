package pl.serkus.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.serkus.model.PublishingHouse;

public class LibrarianPublishingHouseValidator implements Validator{
	@Override
	public boolean supports(Class<?> c) {
		return PublishingHouse.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors err) {
		PublishingHouse publishingHouse = (PublishingHouse)obj;
		
		if(publishingHouse.getName().length() == 0) {
			err.rejectValue("name", "error.emptyField");
		}
		else {
			if(publishingHouse.getName().length() > 30) {
				err.rejectValue("name", "error.librarian.limit.30");
			}
		}
	}

}
