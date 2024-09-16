package scaler.ecommerce.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BrandRequestDto implements Serializable {
    private Long brand_id;
    private String brand_name;
}
