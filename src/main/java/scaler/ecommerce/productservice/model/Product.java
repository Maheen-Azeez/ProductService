package scaler.ecommerce.productservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    private String imageURL;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Category category;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Brand brand;
}
