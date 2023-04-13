package pl.dchruscinski.controller;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dchruscinski.config.exception.ResourceNotFoundException;
import pl.dchruscinski.entity.ProductCategory;
import pl.dchruscinski.service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class ProductCategoriesController {
    public static final Logger logger = LoggerFactory.getLogger(ProductCategoriesController.class);
    private final ProductCategoryService productCategoryService;

    public ProductCategoriesController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getProductCategories() {
        logger.debug("getProductCategories(): returning product categories list.");

        if (productCategoryService.countProductCategories() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productCategoryService.getProductCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ProductCategory> getProductCategory(@PathVariable Integer categoryId) {
        if (!productCategoryService.existsById(categoryId)) {
            throw new ResourceNotFoundException("getProductCategory(): cannot find product category with ID:"
                    + categoryId);
        }

        logger.debug("getProductCategory(): returning product category with ID: {}.", categoryId);
        return ResponseEntity.ok(productCategoryService.getProductCategory(categoryId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countProductCategories() {
        logger.debug("countProductCategories(): returning product categories count.");
        return ResponseEntity.ok(productCategoryService.countProductCategories());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ProductCategory> addProductCategory(@RequestBody ProductCategory productCategory) {
        logger.debug("addProduct(): adding new product category: {}.", productCategory.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryService.addProductCategory(productCategory));
    }

    @Transactional
    @PutMapping("/{categoryId}")
    public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory, @PathVariable Integer categoryId) {
        if (!productCategoryService.existsById(categoryId)) {
            throw new ResourceNotFoundException("getProductCategory(): cannot find product category with ID:"
                    + categoryId);
        }

        logger.debug("updateProductCategory(): updating product category with ID: {} with values: {}.",
                categoryId, productCategory.toString());
        return ResponseEntity.ok(productCategoryService.updateProductCategory(productCategory, categoryId));
    }

    @Transactional
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ProductCategory> deleteProductCategory(@PathVariable Integer categoryId) {
        if (!productCategoryService.existsById(categoryId)) {
            throw new ResourceNotFoundException("getProductCategory(): cannot find product category with ID:"
                    + categoryId);
        }

        logger.debug("deleteProductCategory(): deleting product category with ID: {}.", categoryId);
        productCategoryService.deleteProductCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }
}