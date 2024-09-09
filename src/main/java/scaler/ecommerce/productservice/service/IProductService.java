package scaler.ecommerce.productservice.service;

import scaler.ecommerce.productservice.model.Product;

import java.util.List;

public interface IProductService {
    public Product getProduct(int id);
    public List<Product> getProducts();
    public List<Product> getProductsByCategory(String category);
    public List<Product> getProductsByCategory(String category, String brand);
    public List<Product> getProductsByBrand(String brand);
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(int id);

}
