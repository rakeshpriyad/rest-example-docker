package raj.aayush.example.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import raj.aayush.example.domain.Book;
import raj.aayush.example.service.BookService;
import raj.aayush.example.service.exception.BookAlreadyExistsException;

@RestController
public class BookController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	private final BookService bookService;

	@Autowired
	public BookController(final BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = "/books", method = RequestMethod.POST, consumes = { "application/json" })
	public Book saveBook(@RequestBody @Valid final Book book) {
		LOGGER.debug("Received request to create the {}", book);
		return bookService.saveBook(book);
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = { "application/json" })
	public List<Book> listBooks() {
		LOGGER.debug("Received request to list all books");
		return bookService.getList();
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public Book singleBook(@PathVariable Long id) {
		LOGGER.debug("Received request to list a specific book");
		Optional<Book> book =bookService.getBook(id);
		if(book.isPresent()) {
			return book.get();
		}
		return null;
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable Long id) {
		LOGGER.debug("Received request to delete a specific book");
		bookService.deleteBook(id);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleUserAlreadyExistsException(BookAlreadyExistsException e) {
		return e.getMessage();
	}
}