package scaler.ecommerce.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import scaler.ecommerce.productservice.model.Brand;

public interface BrandRepo extends JpaRepository<Brand, Long> {
    @Query("select b from Brand b where b.title = :title")
    Brand findByName(@Param("title") String brand);
}
