package app.gangdan.phodoapi.global.exception.handler;

public class NoSuchIdException extends RuntimeException {
    public NoSuchIdException(String message) {
        super(message);
    }
}