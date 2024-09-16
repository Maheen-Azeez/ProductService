package scaler.ecommerce.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CategoryRequestDto implements Serializable {
    private Long category_id;
    private String category_name;
}
