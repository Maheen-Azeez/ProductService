package scaler.ecommerce.productservice.exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound() {
    }

    public ProductNotFound(String message) {
        super(message);
    }
}
