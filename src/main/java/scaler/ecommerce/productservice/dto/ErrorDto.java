package scaler.ecommerce.productservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDto implements Serializable {
    private String error;
    private String message;
    public ErrorDto(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
