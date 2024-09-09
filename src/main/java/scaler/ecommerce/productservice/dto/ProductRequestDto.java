package scaler.ecommerce.productservice.dto;

import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String title;
    private String description;
    private String price;
    private String image;
    private String category;
    private String brand;
}
