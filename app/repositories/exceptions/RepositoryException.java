package repositories.exceptions;

/**
 * Created by Lilium on 19.1.2017.
 */
public class RepositoryException extends Exception {

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}