package scaler.ecommerce.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductRequestDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String price;
    private String image;
    private CategoryRequestDto category;
    private BrandRequestDto brand;
}
