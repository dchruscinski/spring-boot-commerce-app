package pl.dchruscinski.controller;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dchruscinski.config.exception.ResourceNotFoundException;
import pl.dchruscinski.entity.Product;
import pl.dchruscinski.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        logger.debug("getProducts(): returning products list.");

        if (productService.countProducts() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        if (!productService.existsById(productId)) {
            throw new ResourceNotFoundException("getProduct(): cannot find product with ID:" + productId);
        }

        logger.debug("getProduct(): returning product with ID: {}.", productId);
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countProducts() {
        logger.debug("getProductCount(): returning products count.");
        return ResponseEntity.ok(productService.countProducts());
    }

    @GetMapping("/count/category/{categoryId}")
    public ResponseEntity<Integer> countProductsByCategory(@PathVariable Integer categoryId) {
        logger.debug("getProductCount(): returning products count.");
        return ResponseEntity.ok(productService.countProductsByCategory(categoryId));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        logger.debug("addProduct(): adding new product: {}.", product.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @Transactional
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Integer productId) {
        if (!productService.existsById(productId)) {
            throw new ResourceNotFoundException("getProduct(): cannot find product with ID:" + productId);
        }

        logger.debug("updateProduct(): updating product with ID: {} with values: {}.", productId, product.toString());
        return ResponseEntity.ok(productService.updateProduct(product, productId));
    }

    @Transactional
    @PatchMapping("/{productId}")
    public ResponseEntity<?> toggleProductAvailability(@PathVariable Integer productId){
        if (!productService.existsById(productId)) {
            throw new ResourceNotFoundException("getProduct(): cannot find product with ID:" + productId);
        }

        productService.toggleProductAvailability(productId);
        logger.debug("toggleProductAvailability(): toggling availability for product with ID: {}.", productId);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId) {
        if (!productService.existsById(productId)) {
            throw new ResourceNotFoundException("getProduct(): cannot find product with ID:" + productId);
        }

        logger.debug("deleteProduct(): deleting product with ID: {}.", productId);
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}