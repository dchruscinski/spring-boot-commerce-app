package pl.dchruscinski.repository;

import pl.dchruscinski.entity.ProductPurchase;

import java.util.List;
import java.util.Optional;

public interface ProductPurchaseRepository {

    List<ProductPurchase> findAll();
    Optional<ProductPurchase> findById(Integer purchaseId);
    List<ProductPurchase> findAllByCustomer(Integer customerId);
    List<ProductPurchase> findAllByProduct(Integer productId);
    boolean existsById(Integer purchaseId);
    ProductPurchase save(ProductPurchase productPurchase);
    void deleteById(Integer purchaseId);
    Integer countProductPurchases();
    Integer countProductPurchasesByCustomer(Integer customerId);
    Integer countProductPurchasesByProduct(Integer productId);
}