package pl.dchruscinski.repository;

import pl.dchruscinski.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository {

    List<ProductCategory> findAll();
    Optional<ProductCategory> findById(Integer productCategoryId);
    boolean existsById(Integer productCategoryId);
    ProductCategory save(ProductCategory productCategory);
    void deleteById(Integer productCategoryId);
    Integer countProductCategories();
}