package pl.dchruscinski.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dchruscinski.entity.Product;
import pl.dchruscinski.repository.ProductRepository;

import java.util.List;

@Repository
public interface AdapterProductRepository extends ProductRepository, JpaRepository<Product, Integer> {

    @Override
    @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM product WHERE id = :id")
    boolean existsById(@Param("id") Integer id);

    @Override
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM product")
    int countProducts();

    @Override
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM product JOIN product_ownership ON id = product_id WHERE customer_id = :customerId")
    int countProductsByCustomerId(@Param("customerId") Integer customerId);

    @Override
    @Query(nativeQuery = true, value = "SELECT * FROM product JOIN product_ownership ON id = product_id WHERE customer_id = :customerId")
    List<Product> getProductsByCustomerId(@Param("customerId") Integer customerId);
}