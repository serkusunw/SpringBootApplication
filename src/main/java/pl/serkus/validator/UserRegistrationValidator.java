package pl.serkus.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.serkus.model.User;

public class UserRegistrationValidator implements Validator {

	User receivedUserFromDB = null;
	
	@Override
	public boolean supports(Class<?> c) {
		return User.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors err) {
		User user = (User)obj;

		String emailRegex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
		
		Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(user.getEmail());
        
        if (!matcher.matches()) {
        	err.rejectValue("email", "error.wrongEmail");
        }// zla skladnia email
        
		if (receivedUserFromDB != null) {
			err.rejectValue("email", "error.emailExists");
		}// email istnieje
		
		if (user.getEmail().length() == 0) {
			err.rejectValue("email", "error.emptyField");
		}// puste pole haslo
        
		if (user.getPassword().length() == 0) {
			err.rejectValue("password", "error.emptyField");
		}// puste pole haslo
		
		if (user.getPasswordCheck().length() == 0) {
			err.rejectValue("passwordCheck", "error.emptyField");
		}// puste pole
		
		if(user.getPassword().length() != 0 && user.getPasswordCheck().length() != 0 && !user.getPassword().equals(user.getPasswordCheck())) {
			err.rejectValue("passwordCheck", "error.wrongPassword");
		}// zle haslo
		
		if (user.getName().length() == 0) {
			err.rejectValue("name", "error.emptyField");
		}// puste pole
		
		if (user.getSurname().length() == 0) {
			err.rejectValue("surname", "error.emptyField");
		}// puste pole
		
		if (user.getDate().length() == 0) {
			err.rejectValue("age", "error.emptyField");
		}// puste pole
		
        if (user.getAddress().getCity().length() == 0) {
        	err.rejectValue("address.city", "error.emptyField");
        }
        
        if (user.getAddress().getStreet().length() == 0) {
        	err.rejectValue("address.street", "error.emptyField");
        	
        }
        
        if (user.getAddress().getPostal_code().length() == 0) {
        	err.rejectValue("address.postal_code", "error.emptyField");
        }
        
        if (user.getAddress().getNumber().length() == 0) {
        	err.rejectValue("address.number", "error.emptyField");
        }
	}
	
	public UserRegistrationValidator(User receivedUserFromDB){
		this.receivedUserFromDB = receivedUserFromDB;
	}

}
