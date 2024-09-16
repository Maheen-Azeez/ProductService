package scaler.ecommerce.productservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    private String title;
    private String description;
    private Double price;
    private String imageURL;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    private Category category;
    @JoinColumn(name = "brand_id")
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Brand brand;
}
