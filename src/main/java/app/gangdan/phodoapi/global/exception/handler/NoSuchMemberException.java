package app.gangdan.phodoapi.global.exception.handler;

public class NoSuchMemberException extends RuntimeException {
    public NoSuchMemberException(String message) {
        super(message);
    }
}