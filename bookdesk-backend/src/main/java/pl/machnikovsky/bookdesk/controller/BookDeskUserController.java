package pl.machnikovsky.bookdesk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.machnikovsky.bookdesk.model.Book;
import pl.machnikovsky.bookdesk.model.BookDeskUser;
import pl.machnikovsky.bookdesk.model.UserDTO;
import pl.machnikovsky.bookdesk.service.BookDeskUserService;

import java.util.List;

@RestController("/user")
@CrossOrigin("*")
public class BookDeskUserController {

    BookDeskUserService bookDeskUserService;

    public BookDeskUserController(BookDeskUserService bookDeskUserService) {
        this.bookDeskUserService = bookDeskUserService;

    }

    @GetMapping("/all")
    public List<BookDeskUser> getUsers() {
        return bookDeskUserService.getUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        return bookDeskUserService.login(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        return bookDeskUserService.register(userDTO);
    }

    @PostMapping("/books/add")
    public void addBookToUser(@RequestBody Book book) {
        bookDeskUserService.addBookToUser(book);
    }


}
