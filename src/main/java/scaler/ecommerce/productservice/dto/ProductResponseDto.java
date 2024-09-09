package scaler.ecommerce.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import scaler.ecommerce.productservice.model.Product;

@Getter
@Setter
public class ProductResponseDto {
    private Long product_id;
    private String title;
    private String description;
    private double price;
    private String image;
    private  CategoryResponseDto category;
    private BrandResponseDto brand;

    public ProductResponseDto(Product product) {
        this.setProduct_id(product.getId());
        this.setTitle(product.getTitle());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setImage(product.getImageURL());
        this.category = new CategoryResponseDto();
        this.brand = new BrandResponseDto();
        this.category.setCategory_id(product.getCategory().getId());
        this.category.setCategory_name(product.getCategory().getTitle());
        this.brand.setBrand_id(product.getBrand().getId());
        this.brand.setBrand_name(product.getBrand().getTitle());
    }
}
