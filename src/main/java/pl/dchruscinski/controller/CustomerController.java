package pl.dchruscinski.controller;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dchruscinski.entity.Customer;
import pl.dchruscinski.entity.Product;
import pl.dchruscinski.service.CustomerService;
import pl.dchruscinski.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public CustomerController(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        logger.debug("getCustomers(): returning customers list.");

        if (customerService.countCustomers() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("/{customerId}")
    // public Customer getCustomer(@PathVariable("customerId") Integer id) {
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer customerId) {
        if (!customerService.existsById(customerId)) {
            logger.warn("getCustomer(): cannot find customer with ID: {}.", customerId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("getCustomer(): returning customer with ID: {}.", customerId);
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCustomerCount() {
        logger.debug("getCustomerCount(): returning customer count.");
        return ResponseEntity.ok(customerService.countCustomers());
    }

    @GetMapping("/{customerId}/products")
    public ResponseEntity<List<Product>> getProductsByCustomerId(@PathVariable Integer customerId) {
        logger.debug("getProductsByCustomerId(): returning products list, where owner is customer with ID: {}.",
                customerId);

        if (productService.countProductsByCustomerId(customerId) == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productService.getProductsByCustomerId(customerId));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        logger.debug("addCustomer(): adding new customer: {}.", customer.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
    }

    @Transactional
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Integer customerId) {
        if (!customerService.existsById(customerId)) {
            logger.warn("updateCustomer(): cannot find customer with ID: {}.", customerId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("updateCustomer(): updating customer with ID: {} with values: {}.", customerId, customer.toString());
        return ResponseEntity.ok(customerService.updateCustomer(customer, customerId));
    }

    @Transactional
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer customerId) {
        if (!customerService.existsById(customerId)) {
            logger.warn("deleteCustomer(): cannot find customer with ID: {}.", customerId);
            return ResponseEntity.notFound().build();
        }

        logger.debug("deleteCustomer(): deleting customer with ID: {}.", customerId);
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }
}