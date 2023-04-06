package pl.dchruscinski.controller;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dchruscinski.entity.ProductOwnership;
import pl.dchruscinski.service.ProductOwnershipService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ownerships")
public class ProductOwnershipController {
    public static final Logger logger = LoggerFactory.getLogger(ProductOwnershipController.class);
    private final ProductOwnershipService productOwnershipService;

    @Autowired
    public ProductOwnershipController(ProductOwnershipService productOwnershipService) {
        this.productOwnershipService = productOwnershipService;
    }

    @GetMapping
    public ResponseEntity<List<ProductOwnership>> getProductOwnerships() {
        logger.debug("getProductOwnerships(): returning ownerships list.");

        if (productOwnershipService.countProductOwnerships() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productOwnershipService.getProductOwnerships());
    }

    @GetMapping("/customers/{customerId}/products/{productId}")
    public ResponseEntity<ProductOwnership> getProductOwnership(@PathVariable Integer customerId, @PathVariable Integer productId) {
        if (!productOwnershipService.existsById(customerId, productId)) {
            logger.warn("getProductOwnership(): cannot find ownership with customer ID: {} and product ID: {}.",
                    customerId, productId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("getProductOwnership(): returning product ownership with customer ID: {} and product ID: {}.",
                customerId, productId);
        return ResponseEntity.ok(productOwnershipService.getProductByCustomerIdAndProductId(customerId, productId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getProductOwnershipCount() {
        logger.debug("getProductOwnershipCount(): returning ownership count.");
        return ResponseEntity.ok(productOwnershipService.countProductOwnerships());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProductOwnership> addProductOwnership(@RequestBody ProductOwnership productOwnership) {
        logger.debug("addProductOwnership(): adding new product ownership: {}.", productOwnership.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(productOwnershipService.addProductOwnership(productOwnership));
    }

    /*

    @Transactional
    @PutMapping("/customers/{customerId}/products/{productId}")
    public ResponseEntity<ProductOwnership> updateProductOwnership(@RequestBody ProductOwnership productOwnership,
                                                                   @PathVariable Integer customerId,
                                                                   @PathVariable Integer productId) {

        if (!productOwnershipService.existsById(customerId, productId)) {
            logger.warn("updateProductOwnership(): cannot find product with customer ID: {} and product ID: {}.",
                    customerId, productId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("updateProduct(): updating ownership with customer ID: {} and product ID: {} with values: {}.", customerId, productId, productOwnership.toString());
        return ResponseEntity.ok(productOwnershipService.addProductOwnership(product, productId));
    }

     */

    @Transactional
    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductOwnership> deleteProductOwnership(@PathVariable Integer customerId,
                                                                   @PathVariable Integer productId) {
        if (!productOwnershipService.existsById(customerId, productId)) {
            logger.warn("deleteProductOwnership(): cannot find product with customer ID: {} and product ID: {}.",
                    customerId, productId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("deleteProductOwnership(): deleting product with customer ID: {} and product ID: {}.",
                customerId, productId);
        productOwnershipService.deleteProductOwnership(customerId, productId);
        return ResponseEntity.noContent().build();
    }
}