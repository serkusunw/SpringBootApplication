package pl.serkus.service;

import java.util.List;
import pl.serkus.model.Book;

public interface SearchService {

	public List<Book> searchBooks(String keyword);
}
