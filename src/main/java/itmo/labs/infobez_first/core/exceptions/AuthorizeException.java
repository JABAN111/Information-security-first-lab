package itmo.labs.infobez_first.core.exceptions;

public class AuthorizeException extends RuntimeException {
    public AuthorizeException(String message) {
        super(message);
    }
}
