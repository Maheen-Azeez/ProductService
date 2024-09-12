package scaler.ecommerce.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto {
    private Long category_id;
    private String category_name;
}
