package pl.serkus.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.serkus.model.Book;
import pl.serkus.repository.BookRepository;
import pl.serkus.service.SearchService;

@Service
@Transactional
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> searchBooks(String keyword) {
		
		return bookRepository.findByBookKeyword(keyword);
	}
	
	
}
