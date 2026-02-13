package zona_fit.exceptions;

public class ApplicationException  extends RuntimeException{
    public ApplicationException(String message){
        super(message);
    }

    public ApplicationException(String message, Throwable cause){
        super(message, cause);
    }
}
