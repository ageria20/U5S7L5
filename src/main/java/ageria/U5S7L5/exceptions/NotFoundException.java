package ageria.U5S7L5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Element with id: " + id + " NOT FOUND");
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
