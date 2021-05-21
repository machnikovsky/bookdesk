package pl.machnikovsky.bookdesk.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String nameFormula = "User with a username %s was not found.";
    private static final String idFormula = "User with a username %s was not found.";

    public UserNotFoundException(String name) {
        super(String.format(nameFormula, name));
    }

    public UserNotFoundException(int id) {
        super(String.format(idFormula, id));
    }

}
