package scaler.ecommerce.productservice.exception;

public class InvalidArgument extends RuntimeException {
    public InvalidArgument() {
    }

    public InvalidArgument(String message) {
        super(message);
    }
}
