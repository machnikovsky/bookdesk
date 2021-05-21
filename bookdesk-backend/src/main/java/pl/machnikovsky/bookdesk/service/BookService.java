package pl.machnikovsky.bookdesk.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.machnikovsky.bookdesk.exception.BookNotFoundException;
import pl.machnikovsky.bookdesk.model.Book;
import pl.machnikovsky.bookdesk.model.BookDeskUser;
import pl.machnikovsky.bookdesk.repository.BookDeskUserRepository;
import pl.machnikovsky.bookdesk.repository.BookRepository;
import pl.machnikovsky.bookdesk.security.BookDeskUserDetails;

import java.util.List;

@Service
public class BookService {

    public BookRepository bookRepository;
    public BookDeskUserRepository bookDeskUserRepository;

    public BookService(BookRepository bookRepository, BookDeskUserRepository bookDeskUserRepository) {
        this.bookRepository = bookRepository;
        this.bookDeskUserRepository = bookDeskUserRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public ResponseEntity<Book> addBook(Book book) {
        String regex = "https://(\\w|\\W)+";
        if (!book.getUrl().matches(regex)) {
            book.setUrl("https://i.pinimg.com/originals/a0/69/7a/a0697af2de64d67cf6dbb2a13dbc0457.png");
        }
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public void deleteBookById(int id) {
        bookRepository.delete(bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }


    public List<Book> getMyBooks() {
        BookDeskUserDetails principal = (BookDeskUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookDeskUser bookDeskUser = bookDeskUserRepository.findByUsernameIgnoreCase(principal.getUsername()).get();
        return bookDeskUser.getBooks();
    }

    public ResponseEntity<Book> updateBook(Book updatedBook, int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setShortSummary(updatedBook.getShortSummary());
        book.setLongSummary(updatedBook.getLongSummary());
        book.setUrl(updatedBook.getUrl());

        return new ResponseEntity(bookRepository.save(book), HttpStatus.OK);
    }
}
