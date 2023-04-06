package pl.dchruscinski.repository;

import pl.dchruscinski.entity.ProductOwnership;

import java.util.List;
import java.util.Optional;

public interface ProductOwnershipRepository {

    List<ProductOwnership> findAll();
    Optional<ProductOwnership> findById(Integer customerId, Integer productId);
    boolean existsById(Integer customerId, Integer productId);
    ProductOwnership save(ProductOwnership productOwnership);
    void deleteById(Integer customerId, Integer productId);
    int countProductOwnerships();
}