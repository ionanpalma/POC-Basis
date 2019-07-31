package lol.api.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -2261481030546787547L;

    public BadRequestException(String message) {
        super(message);
    }

}