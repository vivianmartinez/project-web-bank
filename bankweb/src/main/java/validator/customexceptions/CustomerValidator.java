package validator.customexceptions;

public class CustomerValidator extends Exception{
    public CustomerValidator() {
    }
    public CustomerValidator(String message) {
        super(message);
    }
}
