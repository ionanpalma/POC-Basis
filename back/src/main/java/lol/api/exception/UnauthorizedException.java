package lol.api.exception;

public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = -2261481030546787547L;

    public UnauthorizedException(String message) {
        super(message);
    }

}