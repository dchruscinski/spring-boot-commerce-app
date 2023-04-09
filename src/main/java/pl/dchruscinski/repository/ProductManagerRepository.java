package pl.dchruscinski.repository;

import pl.dchruscinski.entity.ProductManager;

import java.util.List;
import java.util.Optional;

public interface ProductManagerRepository {

    List<ProductManager> findAll();
    Optional<ProductManager> findById(Integer productManagerId);
    boolean existsById(Integer productManagerId);
    ProductManager save(ProductManager productManager);
    void deleteById(Integer productManagerId);
    Integer countProductManagers();
}