package pl.machnikovsky.bookdesk.model;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.*;

@Entity
public class BookDeskUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String password;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="book_desk_user_roles")
    @Column(name="role")
    private Collection<UserRole> roles = new HashSet<>();


    @OneToMany
    private List<Book> books;

    public BookDeskUser(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public BookDeskUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = Set.of(UserRole.USER);
        this.books = new ArrayList<>();
    }

    public BookDeskUser() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<UserRole> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
