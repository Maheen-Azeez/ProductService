package scaler.ecommerce.productservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import scaler.ecommerce.productservice.builder.ProductBuilder;
import scaler.ecommerce.productservice.dto.ProductRequestDto;
import scaler.ecommerce.productservice.dto.ProductResponseDto;
import scaler.ecommerce.productservice.exception.InvalidArgument;
import scaler.ecommerce.productservice.exception.ProductNotFound;
import scaler.ecommerce.productservice.model.Product;
import scaler.ecommerce.productservice.service.IProductService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;
    public ProductController(@Qualifier("ProductService") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getProducts() throws ProductNotFound {
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            throw new ProductNotFound("No products exists");
        }
        List<ProductResponseDto> productResponse = new ArrayList<>();
        for (Product product : products) {
            productResponse.add(new ProductResponseDto(product));
        }
        return ResponseEntity.ok(productResponse);
    }
    @GetMapping("paginated")
    public ResponseEntity<List<ProductResponseDto>> getPaginatedProducts(@RequestParam int pageNumber,@RequestParam int pageSize) throws ProductNotFound {
        Page<Product> products = productService.getProducts(pageNumber, pageSize);
        if (products.getContent().isEmpty()) {
            throw new ProductNotFound("No products exists");
        }
        List<ProductResponseDto> productResponse = new ArrayList<>();
        for (Product product : products.getContent()) {
            productResponse.add(new ProductResponseDto(product));
        }
        return ResponseEntity.ok(productResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") Long id) throws ProductNotFound {
        Product product = productService.getProduct(id);
        if(product == null) {
            throw new ProductNotFound("No product found with ID: " + id + ". Please check the ID and try again.");
        }
        return ResponseEntity.ok(new ProductResponseDto(product));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidArgument {
        validateCreateProductRequest(productRequestDto);
        Product product = ProductBuilder.convertToProduct(productRequestDto);
        Product createdProduct = productService.addProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(createdProduct.getId());
        return ResponseEntity.created(location).body(new ProductResponseDto(createdProduct));
    }
    @PutMapping()
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto productRequestDto) throws ProductNotFound, InvalidArgument{
        validateUpdateProductRequest(productRequestDto);
        Product product = ProductBuilder.convertToProduct(productRequestDto);
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(new ProductResponseDto(updatedProduct));
    }
    private void validateCreateProductRequest(ProductRequestDto productRequestDto) throws InvalidArgument{
        if(productRequestDto == null) {
            throw new InvalidArgument("Product details are required");
        }
    }
    private void validateUpdateProductRequest(ProductRequestDto productRequestDto) throws InvalidArgument, ProductNotFound {
        if (productRequestDto == null || productRequestDto.getId() == null) {
            throw new InvalidArgument("Product details and ID are required");
        }
    }
    private void validateDeleteProductRequest(Long id) throws ProductNotFound,InvalidArgument{
        if(id == null) {
            throw new InvalidArgument("Product ID is required");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> DeleteProduct(@PathVariable Long id) throws ProductNotFound, InvalidArgument {
        validateDeleteProductRequest(id);
        Product deletedProduct = productService.deleteProduct(id);
        return ResponseEntity.ok(new ProductResponseDto(deletedProduct));
    }
    @GetMapping("/category")
    public String getProductsByCategory(@RequestParam String category) {
        return null;
    }
    @GetMapping("/category-and-brand")
    public String getProductsByCategoryAndBrand(@RequestParam String category, @RequestParam String brand) {
        return null;
    }
    @GetMapping("/brand")
    public String getProductsByBrand(@RequestParam String brand) {
        return null;
    }
}

