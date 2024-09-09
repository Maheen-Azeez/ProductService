package scaler.ecommerce.productservice.controller;

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
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
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
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable int id) throws ProductNotFound {
        Product product = productService.getProduct(id);
        if(product == null) {
            throw new ProductNotFound("No product found with ID: " + id + ". Please check the ID and try again.");
        }
        return ResponseEntity.ok(new ProductResponseDto(product));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = ProductBuilder.convertToProduct(productRequestDto);
        Product createdProduct = productService.addProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(createdProduct.getId());
        return ResponseEntity.created(location).body(new ProductResponseDto(createdProduct));
    }
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id,ProductRequestDto productRequestDto) throws ProductNotFound {
        return null;
    }
    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable int id) {
        return null;
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

