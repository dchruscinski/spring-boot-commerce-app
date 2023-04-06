package pl.dchruscinski.repository;

import pl.dchruscinski.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();
    Optional<Product> findById(Integer id);
    boolean existsById(Integer id);
    Product save(Product product);
    void deleteById(Integer id);
    int countProducts();
    List<Product> getProductsByCustomerId(Integer customerId);
    int countProductsByCustomerId(Integer customerId);
}