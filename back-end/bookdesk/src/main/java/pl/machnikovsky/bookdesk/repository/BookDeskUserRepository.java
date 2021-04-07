package pl.machnikovsky.bookdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.machnikovsky.bookdesk.model.BookDeskUser;

import java.util.Optional;

@Repository
public interface BookDeskUserRepository extends JpaRepository<BookDeskUser, Integer> {

    Optional<BookDeskUser> findByUsernameIgnoreCase(String username);
}
