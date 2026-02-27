package zona_fit.exceptions;

public class DatabaseException extends ApplicationException {
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(String message) {
        super(message);
    }

}
