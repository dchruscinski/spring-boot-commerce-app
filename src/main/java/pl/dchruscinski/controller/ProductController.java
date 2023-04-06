package pl.dchruscinski.controller;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dchruscinski.entity.Customer;
import pl.dchruscinski.entity.Product;
import pl.dchruscinski.service.CustomerService;
import pl.dchruscinski.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private final CustomerService customerService;

    @Autowired
    public ProductController(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
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
            logger.warn("getProduct(): cannot find product with ID: {}.", productId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("getProduct(): returning product with ID: {}.", productId);
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getProductCount() {
        logger.debug("getProductCount(): returning product count.");
        return ResponseEntity.ok(productService.countProducts());
    }

    @GetMapping("/{productId}/customers")
    public ResponseEntity<List<Customer>> getCustomersByProductId(@PathVariable Integer productId) {
        logger.debug("getCustomersByProductId(): returning customers list, where they are owning product with ID: {}.",
                productId);

        if (customerService.countCustomersByProductId(productId) == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(customerService.getCustomersByProductId(productId));
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
            logger.warn("updateProduct(): cannot find product with ID: {}.", productId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("updateProduct(): updating product with ID: {} with values: {}.", productId, product.toString());
        return ResponseEntity.ok(productService.updateProduct(product, productId));
    }

    @Transactional
    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId) {
        if (!productService.existsById(productId)) {
            logger.warn("deleteProduct(): cannot find product with ID: {}.", productId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("deleteProduct(): deleting product with ID: {}.", productId);
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}