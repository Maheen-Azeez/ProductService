package scaler.ecommerce.productservice.service;

import org.springframework.data.domain.Page;
import scaler.ecommerce.productservice.exception.InvalidArgument;
import scaler.ecommerce.productservice.exception.ProductNotFound;
import scaler.ecommerce.productservice.model.Product;

import java.util.List;

public interface IProductService {
    public Product getProduct(Long id);
    public List<Product> getProducts();
    public Page<Product> getProducts(int pageNumber, int pageSize);
    public List<Product> getProductsByCategory(String category);
    public List<Product> getProductsByCategory(String category, String brand);
    public List<Product> getProductsByBrand(String brand);
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(Long id);

}
