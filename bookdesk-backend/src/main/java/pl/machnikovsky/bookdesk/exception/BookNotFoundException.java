package pl.machnikovsky.bookdesk.exception;

public class BookNotFoundException extends RuntimeException {

    private static final String formula = "Book with id %d was not found.";

    public BookNotFoundException(int id) {
        super(String.format(formula, id));
    }
}
