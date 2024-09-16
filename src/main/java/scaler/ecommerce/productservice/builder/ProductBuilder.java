package scaler.ecommerce.productservice.builder;

import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;
import scaler.ecommerce.productservice.dto.BrandRequestDto;
import scaler.ecommerce.productservice.dto.CategoryRequestDto;
import scaler.ecommerce.productservice.dto.FakeStoreProductDto;
import scaler.ecommerce.productservice.dto.ProductRequestDto;
import scaler.ecommerce.productservice.model.Brand;
import scaler.ecommerce.productservice.model.Category;
import scaler.ecommerce.productservice.model.Product;

@NoArgsConstructor
public class ProductBuilder {
    public static Product convertToProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setId(productRequestDto.getId());
        product.setCategory(convertToCategory(productRequestDto.getCategory()));
        product.setBrand(convertToBrand(productRequestDto.getBrand()));
        product.setDescription(productRequestDto.getDescription());
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(parsePrice(productRequestDto.getPrice()));
        product.setImageURL(productRequestDto.getImage());
        return product;
    }

    private static Category convertToCategory(CategoryRequestDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryDto.getCategory_id());
        category.setTitle(categoryDto.getCategory_name());
        return category;
    }

    private static Brand convertToBrand(BrandRequestDto brandDto) {
        if (brandDto == null) {
            return null;
        }
        Brand brand = new Brand();
        brand.setId(brandDto.getBrand_id());
        brand.setTitle(brandDto.getBrand_name());
        return brand;
    }
    @Nullable
    private static Double parsePrice(String price) {
        try {
            return price != null ? Double.parseDouble(price) : null;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format: " + price);
        }
    }
    public static Product convertToProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        Category category = new Category();
        category.setTitle(dto.getCategory());
        product.setCategory(category);
        product.setTitle(dto.getTitle());
        product.setId(dto.getId());
        product.setImageURL(dto.getImage());
        product.setPrice(Double.parseDouble(dto.getPrice()));
        product.setDescription(dto.getDescription());
        return product;
    }
}
