package application.exceptions;

public class IncorrectCredentials extends RuntimeException {
    public IncorrectCredentials(String s) {
        super(s);
    }
}
