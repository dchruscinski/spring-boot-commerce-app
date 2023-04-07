package pl.dchruscinski.repository;

import pl.dchruscinski.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();
    Optional<Customer> findById(Integer customerId);
    boolean existsById(Integer customerId);
    Customer save(Customer customer);
    void deleteById(Integer customerId);
    Integer countCustomers();
}