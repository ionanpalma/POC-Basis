package lol.api.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4449711324362316531L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}