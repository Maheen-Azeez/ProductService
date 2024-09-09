package scaler.ecommerce.productservice.exception;

public class ProductNotFound extends Exception {
    public ProductNotFound() {
    }

    public ProductNotFound(String message) {
        super(message);
    }
}
