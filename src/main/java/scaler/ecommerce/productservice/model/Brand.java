package scaler.ecommerce.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@Entity
public class Brand extends BaseModel implements Serializable {
    @Column(unique = true)
    public String title;
    @JsonIgnore
    @OneToMany(mappedBy = "brand",cascade = CascadeType.PERSIST)
    List<Product> products;
}
