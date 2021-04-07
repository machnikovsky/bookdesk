package pl.machnikovsky.bookdesk.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.machnikovsky.bookdesk.model.Book;
import pl.machnikovsky.bookdesk.model.BookDeskUser;
import pl.machnikovsky.bookdesk.model.LoginDTO;
import pl.machnikovsky.bookdesk.repository.BookDeskUserRepository;
import pl.machnikovsky.bookdesk.security.BookDeskUserDetails;
import pl.machnikovsky.bookdesk.security.BookDeskUserDetailsService;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<?> login(LoginDTO loginDTO) {
        if (!bookDeskUserRepository.findByUsernameIgnoreCase(loginDTO.getLogin()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getLogin(),
                        loginDTO.getPassword()));

        BookDeskUserDetails bookDeskUserDetails = (BookDeskUserDetails) authentication.getPrincipal();

        BookDeskUser user = bookDeskUserRepository.findByUsernameIgnoreCase(loginDTO.getLogin()).get();
        if (user.getPassword().equals(bookDeskUserDetails.getPassword())) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public void addBook(Book book) {
        BookDeskUserDetails principal = (BookDeskUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookDeskUser bookDeskUser = bookDeskUserRepository.findByUsernameIgnoreCase(principal.getUsername()).get();
        bookDeskUser.addBook(book);
        bookDeskUserRepository.save(bookDeskUser);
    }
}
