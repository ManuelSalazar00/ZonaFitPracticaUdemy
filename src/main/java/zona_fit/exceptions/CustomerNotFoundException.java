package zona_fit.exceptions;

public class CustomerNotFoundException extends ServiceException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
