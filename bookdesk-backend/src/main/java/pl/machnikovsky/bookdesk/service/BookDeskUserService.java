package pl.machnikovsky.bookdesk.service;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.machnikovsky.bookdesk.model.Book;
import pl.machnikovsky.bookdesk.model.BookDeskUser;
import pl.machnikovsky.bookdesk.model.UserDTO;
import pl.machnikovsky.bookdesk.repository.BookDeskUserRepository;
import pl.machnikovsky.bookdesk.security.BookDeskUserDetails;
import pl.machnikovsky.bookdesk.security.BookDeskUserDetailsService;

import java.util.List;

@Service
public class BookDeskUserService {

    AuthenticationManager authenticationManager;

    BookDeskUserRepository bookDeskUserRepository;
    BookDeskUserDetailsService bookDeskUserDetailsService;
    PasswordEncoder passwordEncoder;

    public BookDeskUserService(AuthenticationManager authenticationManager,
                               BookDeskUserRepository bookDeskUserRepository,
                               BookDeskUserDetailsService bookDeskUserDetailsService,
                               PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.bookDeskUserRepository = bookDeskUserRepository;
        this.bookDeskUserDetailsService = bookDeskUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<BookDeskUser> getUsers() {
        return bookDeskUserRepository.findAll();
    }

    public void addUser(BookDeskUser user) {
        bookDeskUserRepository.save(user);
    }

    public ResponseEntity<?> login(UserDTO userDTO) {
        if (!bookDeskUserRepository.findByUsernameIgnoreCase(userDTO.getLogin()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getLogin(),
                        userDTO.getPassword()));

        BookDeskUserDetails bookDeskUserDetails = (BookDeskUserDetails) authentication.getPrincipal();

        BookDeskUser user = bookDeskUserRepository.findByUsernameIgnoreCase(userDTO.getLogin()).get();
        if (user.getPassword().equals(bookDeskUserDetails.getPassword())) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public void addBookToUser(Book book) {
        BookDeskUserDetails principal = (BookDeskUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookDeskUser bookDeskUser = bookDeskUserRepository.findByUsernameIgnoreCase(principal.getUsername()).get();
        bookDeskUser.addBook(book);
        bookDeskUserRepository.save(bookDeskUser);
    }

    public ResponseEntity<?> register(UserDTO userDTO) {
        if (bookDeskUserRepository.findByUsernameIgnoreCase(userDTO.getLogin()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        BookDeskUser bookDeskUser = new BookDeskUser(userDTO.getLogin(),
                passwordEncoder.encode(userDTO.getPassword()));
        bookDeskUserRepository.save(bookDeskUser);

        return new ResponseEntity<>(bookDeskUser, HttpStatus.CREATED);
    }
}
