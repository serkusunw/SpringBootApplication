package pl.serkus.validator;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.serkus.model.Book;

public class LibrarianNewBookValidator implements Validator{
	@Override
	public boolean supports(Class<?> c) {
		return Book.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors err) {
		Book book = (Book)obj;
		
		if(book.getTitle().length() == 0) {
			err.rejectValue("title", "error.emptyField");
		}
		else if(book.getTitle().length() > 50){
			err.rejectValue("name", "error.librarian.limit.50");
		}
		
		if(book.getCount() < 1) {
			err.rejectValue("count", "error.librarian.book.limit.min.count");
		}
		else if(book.getCount() > 1000) {
			err.rejectValue("count", "error.librarian.book.limit.max.count");
		}
		
		if(book.getDescription().length() == 0 ) {
			err.rejectValue("description", "error.emptyField");
		}
		
		if(book.getDate().length() == 0) {
			err.rejectValue("date", "error.emptyField");
		}
		else if(Date.valueOf(LocalDate.parse(book.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))).after(new Date(Calendar.getInstance().getTimeInMillis()))) {
			err.rejectValue("date", "error.librarian.book.limit.max.date.now");
		}
		else if(Date.valueOf(LocalDate.parse(book.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))).before(Date.valueOf("1700-01-01"))) {
			err.rejectValue("date", "error.librarian.book.limit.min.date.1700");
		}
	}
}
