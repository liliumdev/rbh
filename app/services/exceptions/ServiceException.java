package services.exceptions;

/**
 * Created by Lilium on 20.1.2017.
 */

public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}