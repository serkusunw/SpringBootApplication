package pl.serkus.validator;

import java.sql.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.serkus.model.User;

public class UserEditValidator implements Validator{

	@Override
	public boolean supports(Class<?> c) {
		return User.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors err) {
		User user = (User)obj;
		
		if(user.getName().length() == 0) {
			err.rejectValue("passwordCheck", "error.emptyField");
		}
		if(user.getSurname().length() == 0) {
			err.rejectValue("surname", "error.emptyField");
		}
		if(user.getAddress().getCity().length() == 0) {
			err.rejectValue("address.city", "error.emptyField");
		}
		if(user.getAddress().getPostal_code().length() == 0) {
			err.rejectValue("address.postal_code", "error.emptyField");
		}
		if(user.getAddress().getStreet().length() == 0) {
			err.rejectValue("address.street", "error.emptyField");
		}
		if(user.getAddress().getNumber().length() == 0) {
			err.rejectValue("address.number", "error.emptyField");
		}
		if(Date.valueOf(user.getDate()).before(Date.valueOf("1900-01-01"))) {
			err.rejectValue("age", "error.date");
		}
	}
}
