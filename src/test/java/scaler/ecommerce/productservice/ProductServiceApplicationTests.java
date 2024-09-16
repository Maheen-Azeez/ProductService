package scaler.ecommerce.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import scaler.ecommerce.productservice.model.Brand;
import scaler.ecommerce.productservice.model.Category;
import scaler.ecommerce.productservice.model.Product;
import scaler.ecommerce.productservice.repository.ProductRepo;

import java.math.BigDecimal;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired ProductRepo productRepo;
    @Test
    void contextLoads() {
        for (int i = 0; i < 20; i++) {
            Brand brand = new Brand();
            brand.setTitle("Brand " + i);

            Category category = new Category();
            category.setTitle("Category " + i);

            Product product = new Product();
            product.setBrand(brand);
            product.setCategory(category);
            product.setTitle("Product " + i);
            product.setDescription("Description for product " + i);
            product.setPrice(100.0 + i);

            productRepo.save(product);
        }
    }

}
