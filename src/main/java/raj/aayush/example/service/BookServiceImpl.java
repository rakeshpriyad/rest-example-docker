package raj.aayush.example.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import raj.aayush.example.domain.Book;
import raj.aayush.example.repository.BookRepository;
import raj.aayush.example.service.exception.BookAlreadyExistsException;

@Service
@Validated
public class BookServiceImpl implements BookService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	private final BookRepository repository;

	@Autowired
	public BookServiceImpl(final BookRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public Book saveBook(@NotNull @Valid final Book book) {
		LOGGER.debug("Creating {}", book);
		Optional<Book> bookOptional = repository.findById(book.getId());
		if (!bookOptional.isPresent()) {
			throw new BookAlreadyExistsException(String.format("There already exists a book with id=%s", book.getId()));
		}
		return repository.save(book);
	}

	@Transactional(readOnly = true)
	public List<Book> getList() {
		LOGGER.debug("Retrieving the list of all users");
		return repository.findAll();
	}

	public Optional<Book> getBook(Long bookId) {
		return repository.findById(bookId);
	}

	@Transactional
	public void deleteBook(final Long bookId) {
		LOGGER.debug("deleting {}", bookId);
		repository.deleteById(bookId);
	}
}