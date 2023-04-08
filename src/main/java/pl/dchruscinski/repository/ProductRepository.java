package pl.dchruscinski.repository;

import pl.dchruscinski.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();
    Optional<Product> findById(Integer productId);
    boolean existsById(Integer productId);
    Product save(Product product);
    void deleteById(Integer productId);
    Integer countProducts();
    Integer countProductsByCategory(Integer categoryId);
}