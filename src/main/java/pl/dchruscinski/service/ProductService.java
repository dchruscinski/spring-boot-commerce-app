package pl.dchruscinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.dchruscinski.entity.Product;
import pl.dchruscinski.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public boolean existsById(Integer id) {
        return productRepository.existsById(id);
    }

    public Product getProduct(Integer id) {
        Optional<Product> result = productRepository.findById(id);

        Product product = null;
        if (result.isPresent()) {
            product = result.get();
        }

        return product;
    }

    public Product addProduct(@RequestBody Product product) {
        // In case of someone using API will pass ID in JSON - set it to "0" to save new item instead of updating existing.
        product.setId(0);

        return productRepository.save(product);
    }

    public Product updateProduct(@RequestBody Product product, @PathVariable Integer productId) {
        product.setId(productId);
        return productRepository.save(product);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public Integer countProducts() {
        return productRepository.countProducts();
    }

    public Integer countProductsByCategory(Integer categoryId) {
        return productRepository.countProductsByCategory(categoryId);
    }
}