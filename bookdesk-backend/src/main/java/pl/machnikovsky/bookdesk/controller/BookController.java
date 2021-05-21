package pl.machnikovsky.bookdesk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.machnikovsky.bookdesk.model.Book;
import pl.machnikovsky.bookdesk.service.BookService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
@Transactional
public class BookController {

    public BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<Book> getBooks() {
      return bookService.getBooks();
    }

    @GetMapping("/my")
    public List<Book> getMyBooks() {
        return bookService.getMyBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBookById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book updatedBook, @PathVariable int id) {
        return bookService.updateBook(updatedBook, id);
    }



}
