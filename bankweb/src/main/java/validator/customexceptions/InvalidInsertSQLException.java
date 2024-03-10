package validator.customexceptions;

public class InvalidInsertSQLException extends Exception {

    public InvalidInsertSQLException() {
    }

    public InvalidInsertSQLException(String message) {
        super(message);
    }
}
