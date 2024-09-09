package scaler.ecommerce.productservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import scaler.ecommerce.productservice.model.Product;


public interface ProductRepo extends JpaRepository<Product, Integer> {
}
