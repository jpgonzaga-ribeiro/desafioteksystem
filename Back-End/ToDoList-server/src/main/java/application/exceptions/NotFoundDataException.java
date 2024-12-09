package application.exceptions;

public class NotFoundDataException extends RuntimeException {
    public NotFoundDataException() {
        super("Not found data");
    }
}
