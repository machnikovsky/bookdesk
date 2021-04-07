package pl.machnikovsky.bookdesk.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.machnikovsky.bookdesk.model.Book;
import pl.machnikovsky.bookdesk.model.BookDeskUser;
import pl.machnikovsky.bookdesk.model.LoginDTO;
import pl.machnikovsky.bookdesk.service.BookDeskUserService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BookDeskUserController {

    BookDeskUserService bookDeskUserService;
    PasswordEncoder passwordEncoder;

    public BookDeskUserController(BookDeskUserService bookDeskUserService, PasswordEncoder passwordEncoder) {
        this.bookDeskUserService = bookDeskUserService;
        this.passwordEncoder = passwordEncoder;

        BookDeskUser user = new BookDeskUser("user", passwordEncoder.encode("password"));
        bookDeskUserService.addUser(user);

    }

    @GetMapping("/users")
    public List<BookDeskUser> getUsers() {
        return bookDeskUserService.getUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        return bookDeskUserService.login(loginDTO);
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {
        bookDeskUserService.addBook(book);
    }


}
