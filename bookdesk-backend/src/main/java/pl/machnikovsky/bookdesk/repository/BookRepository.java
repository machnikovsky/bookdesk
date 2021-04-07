package pl.machnikovsky.bookdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.machnikovsky.bookdesk.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
