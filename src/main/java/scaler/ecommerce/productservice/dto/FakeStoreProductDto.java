package scaler.ecommerce.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;
}
