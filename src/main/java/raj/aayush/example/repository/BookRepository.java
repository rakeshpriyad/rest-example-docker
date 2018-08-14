package raj.aayush.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import raj.aayush.example.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	//Book findOne(Long id);

	//void delete(Long bookId);
}
