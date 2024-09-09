package scaler.ecommerce.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    @Column(unique = true)
    private String title;
    @JsonIgnore
    @OneToMany(mappedBy = "category",cascade = CascadeType.PERSIST)
    List<Product> products;
}
