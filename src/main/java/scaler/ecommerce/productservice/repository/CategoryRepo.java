package scaler.ecommerce.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import scaler.ecommerce.productservice.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.title = :title")
    Category findByName(@Param("title")String name);
}
