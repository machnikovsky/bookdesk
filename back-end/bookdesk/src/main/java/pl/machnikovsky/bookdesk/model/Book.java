package pl.machnikovsky.bookdesk.model;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String author;
    String title;
    @Column(length = 10000)
    String longSummary;
    String shortSummary;
    String url;

    public Book(int id, String author, String title, String shortSummary, String longSummary, String url) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.longSummary = longSummary;
        this.shortSummary = shortSummary;
        this.url = url;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLongSummary() {
        return longSummary;
    }

    public void setLongSummary(String longSummary) {
        this.longSummary = longSummary;
    }

    public String getShortSummary() {
        return shortSummary;
    }

    public void setShortSummary(String shortSummary) {
        this.shortSummary = shortSummary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

