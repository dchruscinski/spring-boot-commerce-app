package pl.dchruscinski.controller;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dchruscinski.entity.ProductPurchase;
import pl.dchruscinski.service.ProductPurchaseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchases")
public class ProductPurchaseController {
    public static final Logger logger = LoggerFactory.getLogger(ProductPurchaseController.class);
    private final ProductPurchaseService productPurchaseService;

    public ProductPurchaseController(ProductPurchaseService productPurchaseService) {
        this.productPurchaseService = productPurchaseService;
    }

    @GetMapping
    public ResponseEntity<List<ProductPurchase>> getProductPurchases() {
        logger.debug("getProductPurchases(): returning purchases list.");

        if (productPurchaseService.countProductPurchases() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productPurchaseService.getProductPurchases());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ProductPurchase>> getProductPurchasesByCustomer(@PathVariable Integer customerId) {
        logger.debug("getProductPurchases(): returning purchases list of customer with ID: {}.", customerId);

        if (productPurchaseService.countProductPurchasesByCustomer(customerId) == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productPurchaseService.getProductPurchasesByCustomer(customerId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductPurchase>> getProductPurchasesByProduct(@PathVariable Integer productId) {
        logger.debug("getProductPurchases(): returning purchases list of product with ID: {}.", productId);

        if (productPurchaseService.countProductPurchasesByProduct(productId) == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productPurchaseService.getProductPurchasesByProduct(productId));
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<ProductPurchase> getProductPurchase(@PathVariable Integer purchaseId) {
        if (!productPurchaseService.existsById(purchaseId)) {
            logger.warn("getProductPurchase(): cannot find purchase with ID: {}.", purchaseId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("getProductPurchase(): returning product purchase with ID: {}.", purchaseId);
        return ResponseEntity.ok(productPurchaseService.getProductPurchase(purchaseId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getProductPurchasesCount() {
        logger.debug("getProductPurchaseCount(): returning purchases count.");
        return ResponseEntity.ok(productPurchaseService.countProductPurchases());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProductPurchase> addProductPurchase(@RequestBody ProductPurchase productPurchase) {
        logger.debug("addProductPurchase(): adding new product purchase: {}.", productPurchase.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(productPurchaseService.addProductPurchase(productPurchase));
    }

    @Transactional
    @PutMapping("/{purchaseId}")
    public ResponseEntity<ProductPurchase> updateProductPurchase(@RequestBody ProductPurchase productPurchase,
                                                                 @PathVariable Integer purchaseId) {

        if (!productPurchaseService.existsById(purchaseId)) {
            logger.warn("updateProductPurchase(): cannot find product purchase with ID: {}.", purchaseId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("updateProductPurchase(): updating purchase with ID: {} with values: {}.", purchaseId, productPurchase.toString());
        return ResponseEntity.ok(productPurchaseService.updateProductPurchase(productPurchase, purchaseId));
    }

    @Transactional
    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<ProductPurchase> deleteProductPurchase(@PathVariable Integer purchaseId) {
        if (!productPurchaseService.existsById(purchaseId)) {
            logger.warn("deleteProductPurchase(): cannot find product purchase with ID: {}.", purchaseId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("deleteProductPurchase(): deleting product purchase with ID: {}.", purchaseId);
        productPurchaseService.deleteProductPurchase(purchaseId);
        return ResponseEntity.noContent().build();
    }
}