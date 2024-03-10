package validator.customexceptions;

public class InvalidUpdateSQLException extends Exception {

    public InvalidUpdateSQLException() {
    }

    public InvalidUpdateSQLException(String message) {
        super(message);
    }
}
