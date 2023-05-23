package app.gangdan.phodoapi.global.exception.handler;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
