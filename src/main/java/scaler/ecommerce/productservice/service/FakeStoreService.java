package scaler.ecommerce.productservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import scaler.ecommerce.productservice.builder.ProductBuilder;
import scaler.ecommerce.productservice.dto.FakeStoreProductDto;
import scaler.ecommerce.productservice.model.Product;

import java.util.List;
@Service("FakeStoreService")
public class FakeStoreService implements IProductService{
    private final RestTemplate restTemplate;
    private final RedisTemplate redisTemplate;
    public FakeStoreService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public Product getProduct(Long id) {
        //check in cache
        Product prodctInCache = (Product) redisTemplate.opsForHash().
                get("products","product" + id);
        if (prodctInCache != null) {
            return prodctInCache;
        }
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if(response.getBody() == null){
            return null;
        }

        Product productInFakeStore = ProductBuilder.convertToProduct(response.getBody());
        redisTemplate.opsForHash().put("products","product" + id,productInFakeStore);
        return productInFakeStore;
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Page<Product> getProducts(int pageNumber, int pageSize) {
        return null;
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
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
