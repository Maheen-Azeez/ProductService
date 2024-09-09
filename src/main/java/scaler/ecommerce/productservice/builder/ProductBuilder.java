package scaler.ecommerce.productservice.builder;

import lombok.NoArgsConstructor;
import scaler.ecommerce.productservice.dto.ProductRequestDto;
import scaler.ecommerce.productservice.model.Brand;
import scaler.ecommerce.productservice.model.Category;
import scaler.ecommerce.productservice.model.Product;

@NoArgsConstructor
public class ProductBuilder {
    public static Product convertToProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        Category category = new Category();
        Brand brand = new Brand();
        category.setTitle(productRequestDto.getCategory());
        brand.setTitle(productRequestDto.getBrand());
        product.setCategory(category);
        product.setBrand(brand);
        product.setDescription(productRequestDto.getDescription());
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(Double.parseDouble(productRequestDto.getPrice()));
        product.setImageURL(productRequestDto.getImage());
        return product;
    }
}
