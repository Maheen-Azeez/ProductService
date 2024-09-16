package scaler.ecommerce.productservice.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import scaler.ecommerce.productservice.exception.InvalidArgument;
import scaler.ecommerce.productservice.exception.ProductNotFound;
import scaler.ecommerce.productservice.model.Brand;
import scaler.ecommerce.productservice.model.Category;
import scaler.ecommerce.productservice.model.Product;
import scaler.ecommerce.productservice.repository.BrandRepo;
import scaler.ecommerce.productservice.repository.CategoryRepo;
import scaler.ecommerce.productservice.repository.ProductRepo;

import java.util.List;

@Service("ProductService")
public class ProductService implements IProductService{
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final BrandRepo brandRepo;
    public ProductService(ProductRepo productRepo,CategoryRepo categoryRepo,BrandRepo brandRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.brandRepo = brandRepo;
    }

    @Override
    public Product getProduct(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public Page<Product> getProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        return productRepo.findAll(pageable);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(String category, String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return List.of();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Product addProduct(Product product) {
        String categoryTitle = product.getCategory().getTitle();
        String brandTitle = product.getBrand().getTitle();
        if (categoryTitle != null  && !categoryTitle.isEmpty()) {
            product.setCategory(createCategory(categoryTitle));
        }
        if (brandTitle != null  && !brandTitle.isEmpty()) {
            product.setBrand(createBrand(brandTitle));
        }
        return productRepo.save(product);
    }

    private Brand createBrand(String brandTitle) {
        Brand brand = brandRepo.findByName(brandTitle);
        if(brand == null) {
            brand = new Brand();
            brand.setTitle(brandTitle);
            brand = brandRepo.save(brand);
        }
        return brand;
    }

    private Category createCategory(String categoryTitle) {
        Category category = categoryRepo.findByName(categoryTitle);
        if(category == null) {
            category = new Category();
            category.setTitle(categoryTitle);
            category = categoryRepo.save(category);
        }
        return category;
    }

    @Override
    public Product updateProduct(Product product) {
        if(!productRepo.existsById(product.getId())) {
            throw new ProductNotFound("No product found with ID: " + product.getId());
        }
        return productRepo.save(product);
    }
    @Override
    public Product deleteProduct(Long id) {
        if(!productRepo.existsById(id)){
            throw new ProductNotFound("No product found with ID: " + id);
        }
        Product productToDelete = productRepo.findById(id).orElse(null);
        productRepo.deleteById(id);
        return productToDelete;
    }
}
