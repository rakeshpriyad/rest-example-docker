package raj.aayush.example.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import raj.aayush.example.domain.Book;

public interface BookService {
	Book saveBook(@NotNull @Valid final Book book);

	List<Book> getList();

	Optional<Book> getBook(Long bookId);

	void deleteBook(final Long bookId);
}
