package pl.dchruscinski.controller;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dchruscinski.entity.Customer;
import pl.dchruscinski.entity.ProductManager;
import pl.dchruscinski.service.ProductManagerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/managers")
public class ProductManagerController {
    public static final Logger logger = LoggerFactory.getLogger(ProductManagerController.class);
    private final ProductManagerService productManagerService;

    public ProductManagerController(ProductManagerService productManagerService) {
        this.productManagerService = productManagerService;
    }

    @GetMapping
    public ResponseEntity<List<ProductManager>> getProductManagers() {
        logger.debug("getCustomers(): returning product managers list.");

        if (productManagerService.countProductManagers() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productManagerService.getProductManagers());
    }

    @GetMapping("/{managerId}")
    public ResponseEntity<ProductManager> getProductManager(@PathVariable Integer managerId) {
        if (!productManagerService.existsById(managerId)) {
            logger.warn("getProductManager(): cannot find product manager with ID: {}.", managerId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("getProductManager(): returning product manager with ID: {}.", managerId);
        return ResponseEntity.ok(productManagerService.getProductManager(managerId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countProductManagers() {
        logger.debug("countProductManagers(): returning product managers count.");
        return ResponseEntity.ok(productManagerService.countProductManagers());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProductManager> addProductManager(@RequestBody ProductManager productManager) {
        logger.debug("addProductManager(): adding new product manager: {}.", productManager.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(productManagerService.addProductManager(productManager));
    }

    @Transactional
    @PutMapping("/{managerId}")
    public ResponseEntity<ProductManager> updateProductManager(@RequestBody ProductManager productManager,
                                                               @PathVariable Integer managerId) {

        if (!productManagerService.existsById(managerId)) {
            logger.warn("updateProductManager(): cannot find product manager with ID: {}.", managerId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("updateProductManager(): updating product manager with ID: {} with values: {}.",
                managerId, productManager.toString());
        return ResponseEntity.ok(productManagerService.updateProductManager(productManager, managerId));
    }

    @Transactional
    @DeleteMapping("/{managerId}")
    public ResponseEntity<Customer> deleteProductManager(@PathVariable Integer managerId) {
        if (!productManagerService.existsById(managerId)) {
            logger.warn("deleteCustomer(): cannot find product manager with ID: {}.", managerId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("deleteCustomer(): deleting product manager with ID: {}.", managerId);
        productManagerService.deleteProductManagerById(managerId);
        return ResponseEntity.noContent().build();
    }
}