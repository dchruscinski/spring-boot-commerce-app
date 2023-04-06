package pl.dchruscinski.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dchruscinski.entity.Customer;
import pl.dchruscinski.repository.CustomerRepository;

import java.util.List;

@Repository
public interface AdapterCustomerRepository extends CustomerRepository, JpaRepository<Customer, Integer> {

    @Override
    @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM customer WHERE id = :id")
    boolean existsById(@Param("id") Integer id);

    @Override
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM customer")
    int countCustomers();

    @Override
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM customer JOIN product_ownership ON id = customer_id WHERE product_id = :productId")
    int countCustomersByProductId(@Param("productId") Integer productId);

    @Override
    @Query(nativeQuery = true, value = "SELECT * FROM customer JOIN product_ownership ON id = customer_id WHERE product_id = :productId")
    List<Customer> getCustomersByProductId(@Param("productId") Integer productId);
}