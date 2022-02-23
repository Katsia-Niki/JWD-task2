package by.jwd.task2.exception;

public class MedProductException extends Exception {
    public MedProductException() {

    }

    public MedProductException(String message) {
        super(message);
    }

    public MedProductException(Exception e) {
        super(e);
    }

    public MedProductException(String message, Exception e) {
        super(message, e);
    }
}
