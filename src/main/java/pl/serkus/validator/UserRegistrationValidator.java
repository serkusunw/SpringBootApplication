package pl.serkus.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.serkus.model.User;

public class UserRegistrationValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return User.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors err) {
		User user = (User)obj;
		
		if((user.getPassword().length() > 0  && user.getPasswordCheck().length() > 0) && !user.getPassword().equals(user.getPasswordCheck())) {
			err.rejectValue("passwordCheck", "error.wrongPassword");
		}
	}

}
